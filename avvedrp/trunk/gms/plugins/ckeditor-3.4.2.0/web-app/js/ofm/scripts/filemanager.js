/**
 *	Filemanager JS core
 *
 *	filemanager.js
 *
 *	@license	MIT License
 *	@author		Jason Huck - Core Five Labs
 *	@author		Simon Georget <simon (at) linea21 (dot) com>
 *	@copyright	Authors
 */
 
// function to retrieve GET params
$.urlParam = function(name){
	var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results != null) {
	    return results[1] || 0;
    }
    else {
        return 0;
    }
}

/*---------------------------------------------------------
  Setup, Layout, and Status Functions
---------------------------------------------------------*/

// Get localized messages from file 
// through culture var or from URL
if($.urlParam('langCode') != 0) culture = $.urlParam('langCode');
var lg = [];
$.ajax({
  url: ofmBase + '/scripts/languages/'  + culture + '.js',
  async: false,
  dataType: 'json',
  success: function (json) {
    lg = json;
  }
});

// we finalize the FileManager UI initialization 
// with localized text if necessary
if(autoload == true) {
	$('#upload').append(lg.upload);
    $('#gotoroot').append(lg.gotoroot);
	$('#newfolder').append(lg.new_folder);
	$('#grid').attr('title', lg.grid_view);
	$('#list').attr('title', lg.list_view);
	$('#fileinfo h1').append(lg.select_from_left);
	$('#itemOptions a[href$="#select"]').append(lg.select);
	$('#itemOptions a[href$="#download"]').append(lg.download);
	$('#itemOptions a[href$="#rename"]').append(lg.rename);
	$('#itemOptions a[href$="#delete"]').append(lg.del);
}

// Options for alert, prompt, and confirm dialogues.
$.SetImpromptuDefaults({
	overlayspeed: 'fast',
	show: 'fadeIn',
	opacity: 0.4
});

// Forces columns to fill the layout vertically.
// Called on initial page load and on resize.
var setDimensions = function(){
	var newH = $(window).height() - $('#uploader').height();	
	$('#splitter, #filetree, #fileinfo, .vsplitbar').height(newH);
}

// Display Min Path
var displayRoot = function(path){
	if(showFullPath == false)
		return path.replace(fileRoot, "/");
	else 
		return path;
}

// from http://phpjs.org/functions/basename:360
var basename = function(path, suffix) {
    var b = path.replace(/^.*[\/\\]/g, '');

    if (typeof(suffix) == 'string' && b.substr(b.length-suffix.length) == suffix) {
        b = b.substr(0, b.length-suffix.length);
    }
    
    return b;
}

// Sets the folder status, upload, and new folder functions 
// to the path specified. Called on initial page load and 
// whenever a new directory is selected.
var setUploader = function(path){
	$('#currentpath').val(path);
	$('#uploader h1').text(lg.current_folder + displayRoot(path));

    $('#gotoroot').unbind().click(function() {
        getFolderInfo(fileRoot);
    });

	$('#newfolder').unbind().click(function(){
		var foldername =  lg.default_foldername;
		var msg = lg.prompt_foldername + ' : <input id="fname" name="fname" type="text" value="' + foldername + '" />';
		
		var getFolderName = function(v, m){
			if(v != 1) return false;		
			var fname = m.children('#fname').val();		

			if(fname != ''){
				foldername = fname;

				$.getJSON(fileConnector + '?mode=addfolder&path=' + $('#currentpath').val() + '&name=' + foldername + '&space=' + space + '&type=' + type, function(result){
					if(result['Code'] == 0){
						addFolder(result['Parent'], result['Name']);
						getFolderInfo(result['Parent']);
					} else {
						$.prompt(result['Error']);
					}				
				});
			} else {
				$.prompt(lg.no_foldername);
			}
		}
		var btns = {}; 
		btns[lg.create_folder] = true; 
		btns[lg.cancel] = false; 
		$.prompt(msg, {
			callback: getFolderName,
			buttons: btns 
		});	
	});	
}

// Binds specific actions to the toolbar in detail views.
// Called when detail views are loaded.
var bindToolbar = function(data){
	// this little bit is purely cosmetic
	$('#fileinfo').find('button').wrapInner('<span></span>');

	$('#fileinfo').find('button#select').click(function(){
		selectItem(data);
	});
	
	$('#fileinfo').find('button#rename').click(function(){
		var newName = renameItem(data);
		if(newName.length) $('#fileinfo > h1').text(newName);
	});

	$('#fileinfo').find('button#delete').click(function(){
		if(deleteItem(data)) $('#fileinfo').html('<h1>' + lg.select_from_left + '</h1>');
	});
	
	$('#fileinfo').find('button#download').click(function(){
		window.location = fileConnector + '?mode=download&path=' + data['Path'] + '&space=' + space + '&type=' + type;
	});
}

// Converts bytes to kb, mb, or gb as needed for display.
var formatBytes = function(bytes){
	var n = parseFloat(bytes);
	var d = parseFloat(1024);
	var c = 0;
	var u = [lg.bytes,lg.kb,lg.mb,lg.gb];
	
	while(true){
		if(n < d){
			n = Math.round(n * 100) / 100;
			return n + u[c];
		} else {
			n /= d;
			c += 1;
		}
	}
}

/*---------------------------------------------------------
  Item Actions
---------------------------------------------------------*/

// Calls the SetUrl function for FCKEditor compatibility,
// passes file path, dimensions, and alt text back to the
// opening window. Triggered by clicking the "Select" 
// button in detail views or choosing the "Select"
// contextual menu option in list views. 
// NOTE: closes the window when finished.
var selectItem = function(data){
	if(window.opener){
	 	if(window.tinyMCEPopup){
        	// use TinyMCE > 3.0 integration method
            var win = tinyMCEPopup.getWindowArg("window");
			win.document.getElementById(tinyMCEPopup.getWindowArg("input")).value = data['Path'];
            if (typeof(win.ImageDialog) != "undefined") {
				// Update image dimensions
            	if (win.ImageDialog.getImageData)
                 	win.ImageDialog.getImageData();

                // Preview if necessary
                if (win.ImageDialog.showPreviewImage)
					win.ImageDialog.showPreviewImage(data['Path']);
			}
			tinyMCEPopup.close();
			return;
		}
		if($.urlParam('CKEditor')){
			// use CKEditor 3.0 integration method
			window.opener.CKEDITOR.tools.callFunction($.urlParam('CKEditorFuncNum'), webRoot + data['Path']);
		}
        else {
            try {
                window.opener.ckeditorFileBrowserItemSelected(webRoot + data['Path']);
            }
            catch (e) {
                // $.prompt(lg.fck_select_integration + ' -> ' + webRoot + data['Path']);
                // Ignored
            }
        }

		window.close();
	} else {
        $.prompt(lg.fck_select_integration + ' -> ' + webRoot + data['Path']);
	}
}

// Renames the current item and returns the new name.
// Called by clicking the "Rename" button in detail views
// or choosing the "Rename" contextual menu option in 
// list views.
var renameItem = function(data){
	var finalName = '';
	var msg = lg.new_filename + ' : <input id="rname" name="rname" type="text" value="' + data['Filename'] + '" />';

	var getNewName = function(v, m){
		if(v != 1) return false;
		rname = m.children('#rname').val();
		
		if(rname != ''){
			var givenName = rname;	
			var oldPath = data['Path'];	
			var connectString = fileConnector + '?mode=rename&old=' + data['Path'] + '&new=' + givenName + '&space=' + space + '&type=' + type;
		
			$.ajax({
				type: 'GET',
				url: connectString,
				dataType: 'json',
				async: false,
				success: function(result){
					if(result['Code'] == 0){
						var newPath = result['New Path'];
						var newName = result['New Name'];
	
						updateNode(oldPath, newPath, newName);
						
						var title = $("#preview h1").attr("title");

						if (typeof title !="undefined" && title == oldPath) {
							$('#preview h1').text(newName);
						}
						
						if($('#fileinfo').data('view') == 'grid'){
							$('#fileinfo img[alt="' + oldPath + '"]').parent().next('p').text(newName);
							$('#fileinfo img[alt="' + oldPath + '"]').attr('alt', newPath);
						} else {
							$('#fileinfo td[title="' + oldPath + '"]').text(newName);
							$('#fileinfo td[title="' + oldPath + '"]').attr('title', newPath);
						}
										
						$.prompt(lg.successful_rename);
					} else {
						$.prompt(result['Error']);
					}
					
					finalName = result['New Name'];		
				}
			});	
		}
	}
	var btns = {}; 
	btns[lg.rename] = true; 
	btns[lg.cancel] = false; 
	$.prompt(msg, {
		callback: getNewName,
		buttons: btns 
	});
	
	return finalName;
}

// Prompts for confirmation, then deletes the current item.
// Called by clicking the "Delete" button in detail views
// or choosing the "Delete contextual menu item in list views.
var deleteItem = function(data){
	var isDeleted = false;
	var msg = lg.confirmation_delete;
	
	var doDelete = function(v, m){
		if(v != 1) return false;	
		var connectString = fileConnector + '?mode=delete&path=' + data['Path'] + '&space=' + space + '&type=' + type;
	
		$.ajax({
			type: 'GET',
			url: connectString,
			dataType: 'json',
			async: false,
			success: function(result){
				if(result['Code'] == 0){
					removeNode(result['Path']);
					isDeleted = true;
					$.prompt(lg.successful_delete);
				} else {
					isDeleted = false;
					$.prompt(result['Error']);
				}			
			}
		});	
	}
	var btns = {}; 
	btns[lg.yes] = true; 
	btns[lg.no] = false; 
	$.prompt(msg, {
		callback: doDelete,
		buttons: btns 
	});
	
	return isDeleted;
}

/*---------------------------------------------------------
  Functions to Update the File Tree
---------------------------------------------------------*/

// Adds a new node as the first item beneath the specified
// parent node. Called after a successful file upload.
var addNode = function(path, name){
	var ext = name.substr(name.lastIndexOf('.') + 1);
	var thisNode = $('#filetree').find('a[rel="' + path + '"]');
	var parentNode = thisNode.parent();
	var newNode = '<li class="file ext_' + ext + '"><a rel="' + path + name + '/" href="#">' + name + '/</a></li>';
	
	if(!parentNode.find('ul').size()) parentNode.append('<ul></ul>');		
	parentNode.find('ul').prepend(newNode);
	thisNode.click().click();

    if (path == fileRoot) {
        setupFileTree(); // ugly hack!!!
    }

	getFolderInfo(path);

	$.prompt(lg.successful_added_file);
}

// Updates the specified node with a new name. Called after
// a successful rename operation.
var updateNode = function(oldPath, newPath, newName){
	var thisNode = $('#filetree').find('a[rel="' + oldPath + '"]');
	var parentNode = thisNode.parent().parent().prev('a');
	thisNode.attr('rel', newPath).text(newName);
	parentNode.click().click();

}

// Removes the specified node. Called after a successful 
// delete operation.
var removeNode = function(path){
    $('#filetree')
        .find('a[rel="' + path + '"]')
        .parent()
        .fadeOut('slow', function(){ 
            $(this).remove();
        });
    // grid case
    if($('#fileinfo').data('view') == 'grid'){
        $('#contents img[alt="' + path + '"]').parent().parent()
            .fadeOut('slow', function(){ 
                $(this).remove();
        });
    }
    // list case
    else {
        $('table#contents')
            .find('td[title="' + path + '"]')
            .parent()
            .fadeOut('slow', function(){ 
                $(this).remove();
        });
    }
    // remove fileinfo when item to remove is currently selected
    if ($('#preview').length) {
		$('#fileinfo').fadeOut('slow', function(){
			$(this).empty().show();
		});
	}
}

// Adds a new folder as the first item beneath the
// specified parent node. Called after a new folder is
// successfully created.
var addFolder = function(parent, name){
	var newNode = '<li class="directory collapsed"><a rel="' + parent + name + '/" href="#">' + name + '</a><ul class="jqueryFileTree" style="display: block;"></ul></li>';
	var parentNode = $('#filetree').find('a[rel="' + parent + '"]');
	if(parent != fileRoot){
		parentNode.next('ul').prepend(newNode).prev('a').click().click();
	} else {
		// $('#filetree > ul').append(newNode);
        setupFileTree(); // ugly hack!!!
	}
	
	$.prompt(lg.successful_added_folder);
}

/*---------------------------------------------------------
  Functions to Retrieve File and Folder Details
---------------------------------------------------------*/

// Decides whether to retrieve file or folder info based on
// the path provided.
var getDetailView = function(path){
	if(path.lastIndexOf('/') == path.length - 1){
		getFolderInfo(path);
		$('#filetree').find('a[rel="' + path + '"]').click();
	} else {
		getFileInfo(path);
	}
}

// Binds contextual menus to items in list and grid views.
var setMenus = function(action, path){
	$.getJSON(fileConnector + '?mode=getinfo&path=' + path + '&space=' + space + '&type=' + type, function(data){
        var item;
		if($('#fileinfo').data('view') == 'grid'){
			item = $('#fileinfo').find('img[alt="' + data['Path'] + '"]').parent();
		} else {
			item = $('#fileinfo').find('td[title="' + data['Path'] + '"]').parent();
		}
	
		switch(action){
			case 'select':
				selectItem(data);
				break;
			
			case 'download':
				window.location = fileConnector + '?mode=download&path=' + data['Path'] + '&space=' + space + '&type=' + type;
				break;
				
			case 'rename':
				var newName = renameItem(data);
				break;
				
			case 'delete':
				// TODO: When selected, the file is deleted and the
				// file tree is updated, but the grid/list view is not.
				if(deleteItem(data)) item.fadeOut('slow', function(){ $(this).remove(); });
				break;
		}
	});
}

// Retrieves information about the specified file as a JSON
// object and uses that data to populate a template for
// detail views. Binds the toolbar for that detail view to
// enable specific actions. Called whenever an item is
// clicked in the file tree or list views.
var getFileInfo = function(file){
	// Update location for status, upload, & new folder functions.
	var currentpath = file.substr(0, file.lastIndexOf('/') + 1);
	setUploader(currentpath);

	// Include the template.
	var template = '<div id="preview"><img /><h1></h1><dl></dl></div>';
	template += '<form id="toolbar">';
	if(window.opener != null) template += '<button id="select" name="select" type="button" value="Select">' + lg.select + '</button>';
	template += '<button id="download" name="download" type="button" value="Download">' + lg.download + '</button>';
	template += '<button id="rename" name="rename" type="button" value="Rename">' + lg.rename + '</button>';
	template += '<button id="delete" name="delete" type="button" value="Delete">' + lg.del + '</button>';
	template += '<button id="parentfolder">' + lg.parentfolder + '</button>';
	template += '</form>';
	
	$('#fileinfo').html(template);
	$('#parentfolder').click(function() {getFolderInfo(currentpath);});
	
	// Retrieve the data & populate the template.
	$.getJSON(fileConnector + '?mode=getinfo&path=' + file + '&space=' + space + '&type=' + type, function(data){
		if(data['Code'] == 0){
			$('#fileinfo').find('h1').text(data['Filename']).attr('title', file);
			$('#fileinfo').find('img').attr('src',data['Preview']);
			
			var properties = '';
			
			if(data['Properties']['Width'] && data['Properties']['Width'] != '') properties += '<dt>' + lg.dimensions + '</dt><dd>' + data['Properties']['Width'] + 'x' + data['Properties']['Height'] + '</dd>';
			if(data['Properties']['Date Created'] && data['Properties']['Date Created'] != '') properties += '<dt>' + lg.created + '</dt><dd>' + data['Properties']['Date Created'] + '</dd>';
			if(data['Properties']['Date Modified'] && data['Properties']['Date Modified'] != '') properties += '<dt>' + lg.modified + '</dt><dd>' + data['Properties']['Date Modified'] + '</dd>';
			if(data['Properties']['Size'] && data['Properties']['Size'] != '') properties += '<dt>' + lg.size + '</dt><dd>' + formatBytes(data['Properties']['Size']) + '</dd>';
			
			$('#fileinfo').find('dl').html(properties);
			
			// Bind toolbar functions.
			bindToolbar(data);
		} else {
			$.prompt(data['Error']);
		}
	});	
}

// Retrieves data for all items within the given folder and
// creates a list view. Binds contextual menu options.
// TODO: consider stylesheet switching to switch between grid
// and list views with sorting options.
var getFolderInfo = function(path){
	// Update location for status, upload, & new folder functions.
	setUploader(path);

	// Display an activity indicator.
	$('#fileinfo').html('<img id="activity" src="' + ofmBase + '/images/wait30trans.gif" width="30" height="30" />');

	// Retrieve the data and generate the markup.
	$.getJSON(fileConnector + '?path=' + path + '&mode=getfolder&showThumbs=' + showThumbs + '&space=' + space + '&type=' + type, function(data){
		var result = '';
	
		if(data){		
			if($('#fileinfo').data('view') == 'grid'){
				result += '<ul id="contents" class="grid">';
				
				for(key in data){
					var props = data[key]['Properties'];
				
					var scaledWidth = 64;
					var actualWidth = props['Width'];
					if(actualWidth > 1 && actualWidth < scaledWidth) scaledWidth = actualWidth;
				
					result += '<li><div class="clip"><img src="' + data[key]['Preview'] + '" width="' + scaledWidth + '" alt="' + data[key]['Path'] + '" /></div><p>' + data[key]['Filename'] + '</p>';
					if(props['Width'] && props['Width'] != '') result += '<span class="meta dimensions">' + props['Width'] + 'x' + props['Height'] + '</span>';
					if(props['Size'] && props['Size'] != '') result += '<span class="meta size">' + props['Size'] + '</span>';
					if(props['Date Created'] && props['Date Created'] != '') result += '<span class="meta created">' + props['Date Created'] + '</span>';
					if(props['Date Modified'] && props['Date Modified'] != '') result += '<span class="meta modified">' + props['Date Modified'] + '</span>';
					result += '</li>';
				}
				
				result += '</ul>';
			} else {
				result += '<table id="contents" class="list">';
				result += '<thead><tr><th class="headerSortDown"><span>' + lg.name + '</span></th><th><span>' + lg.dimensions + '</span></th><th><span>' + lg.size + '</span></th><th><span>' + lg.modified + '</span></th></tr></thead>';
				result += '<tbody>';
				
				for(key in data){
					var path = data[key]['Path'];
					var props = data[key]['Properties'];					
					result += '<tr>';
					result += '<td title="' + path + '">' + data[key]['Filename'] + '</td>';

					if(props['Width'] && props['Width'] != ''){
						result += ('<td>' + props['Width'] + 'x' + props['Height'] + '</td>');
					} else {
						result += '<td></td>';
					}
					
					if(props['Size'] && props['Size'] != ''){
						result += '<td><abbr title="' + props['Size'] + '">' + formatBytes(props['Size']) + '</abbr></td>';
					} else {
						result += '<td></td>';
					}
					
					if(props['Date Modified'] && props['Date Modified'] != ''){
						result += '<td>' + props['Date Modified'] + '</td>';
					} else {
						result += '<td></td>';
					}
				
					result += '</tr>';					
				}
								
				result += '</tbody>';
				result += '</table>';
			}			
		} else {
			result += '<h1>' + lg.could_not_retrieve_folder + '</h1>';
		}
		
		// Add the new markup to the DOM.
		$('#fileinfo').html(result);
		
		// Bind click events to create detail views and add
		// contextual menu options.
		if($('#fileinfo').data('view') == 'grid'){
			$('#fileinfo').find('#contents li').click(function(){
				var path = $(this).find('img').attr('alt');
				getDetailView(path);
			}).contextMenu({ menu: 'itemOptions' }, function(action, el, pos){
				var path = $(el).find('img').attr('alt');
				setMenus(action, path);
			});
		} else {
			$('#fileinfo').find('td:first-child').each(function(){
				var path = $(this).attr('title');
				var treenode = $('#filetree').find('a[rel="' + path + '"]').parent();
				$(this).css('background-image', treenode.css('background-image'));
			});
			
			$('#fileinfo tbody tr').click(function(){
				var path = $('td:first-child', this).attr('title');
				getDetailView(path);		
			}).contextMenu({ menu: 'itemOptions' }, function(action, el, pos){
				var path = $('td:first-child', el).attr('title');
				setMenus(action, path);
			});
			
			$('#fileinfo').find('table').tablesorter({
				textExtraction: function(node){					
					if($(node).find('abbr').size()){
						return $(node).find('abbr').attr('title');
					} else {					
						return node.innerHTML;
					}
				}
			});
		}
	});
}

/*---------------------------------------------------------
  Initialization
---------------------------------------------------------*/

function setupFileTree() {
	// Creates file tree.
    $('#filetree').fileTree({
		root: fileRoot,
		script: treeConnector + '?space=' + space + '&type=' + type,
		multiFolder: false,
		folderCallback: function(path){ getFolderInfo(path); },
		after: function(data){
			$('#filetree').find('li a').contextMenu(
				{ menu: 'itemOptions' },
				function(action, el, pos){
					var path = $(el).attr('rel');
					setMenus(action, path);
				}
			);
		}
	}, function(file){
		getFileInfo(file);
	});
}

$(function(){
	// Adjust layout.
	setDimensions();
	$(window).resize(setDimensions);

	// Provides support for adjustible columns.
	$('#splitter').splitter({
		initA: 200
	});

	// cosmetic tweak for buttons
	$('button').wrapInner('<span></span>');

	// Set initial view state.
	$('#fileinfo').data('view', 'grid');

	// Set buttons to switch between grid and list views.
	$('#grid').click(function(){
		$(this).addClass('ON');
		$('#list').removeClass('ON');
		$('#fileinfo').data('view', 'grid');
		getFolderInfo($('#currentpath').val());
	});
	
	$('#list').click(function(){
		$(this).addClass('ON');
		$('#grid').removeClass('ON');
		$('#fileinfo').data('view', 'list');
		getFolderInfo($('#currentpath').val());
	});

	// Provide initial values for upload form, status, etc.
	setUploader(fileRoot);

	$('#uploader').attr('action', fileConnector + '?space=' + space + '&type=' + type);

	$('#uploader').ajaxForm({
		target: '#uploadresponse',
		success: function(result){
			eval('var data = ' + $('#uploadresponse').find('textarea').text());

			if(data['Code'] == 0){
				addNode(data['Path'], data['Name']);
                $('#newfile').val('');
			} else {
				$.prompt(data['Error']);
			}
		}
	});

	// Creates file tree.
    setupFileTree();

    // Initial folder view
    getFolderInfo($('#currentpath').val());

	// Disable select function if no window.opener
	if(window.opener == null) $('#itemOptions a[href$="#select"]').remove();
	
	//alert($('#filetree').find('li a.directory'));
    getDetailView(fileRoot);
});
