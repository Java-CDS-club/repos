package in.ac.dei.edrp.client.ProgramSetup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.MessageBoxConfig;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;

import in.ac.dei.edrp.client.CM_ProgramInfoGetter;
import in.ac.dei.edrp.client.CM_entityInfoGetter;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;
import in.ac.dei.edrp.client.Shared.*;


public class Program_Age_Eligibility {
    private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);
    messages msg = GWT.create(messages.class);
    constants cons = GWT.create(constants.class);
    VerticalPanel vp = new VerticalPanel();
    Validator valid = new Validator();
    String entity;
    String entity_id;
    NumberField ageNF1 = new NumberField();
    String creatorid;
    public Program_Age_Eligibility(String userid) {
        this.creatorid = userid;
    }
    public VerticalPanel onModuleLoad() {
        vp.clear();
        vp.setWidth("1200px");
        final CM_ComboBoxes entityTypeCB = new CM_ComboBoxes(creatorid);
        final CM_ComboBoxes entityNameCB = new CM_ComboBoxes(creatorid);
        final CM_ComboBoxes programCB = new CM_ComboBoxes(creatorid);
        final CM_ComboBoxes category_obj1 = new CM_ComboBoxes(creatorid);
        
        Label selectEntityName = new Label(cons.entityName());
        Label selectEntity = new Label(cons.entityType());       
        final NumberField ageNF = new NumberField();
        final Button submitButton = new Button(cons.submit());
        final Button cancelButton = new Button(cons.cancelButton());
        final FormPanel mainForm = new FormPanel();
        FlexTable flex3 = new FlexTable();
        entityTypeCB.onModuleLoad();
        entityNameCB.onModuleLoad();
        category_obj1.onModuleLoad();
        programCB.onModuleLoad();
        programCB.progCombo.disable();
        entityNameCB.entityCombo.disable();                
        ageNF.setAllowBlank(false);
        ageNF.setAllowDecimals(false);
        ageNF.setAllowNegative(false);
        ageNF.setMaxLength(2);
        ageNF1 = ageNF;
        flex3.setCellSpacing(6);
        flex3.setCellPadding(6);
        flex3.setWidget(0, 0, selectEntity);
        flex3.setWidget(0, 1, entityTypeCB.entityDescCB);
        flex3.setWidget(0, 2, selectEntityName);
        flex3.setWidget(0, 3, entityNameCB.entityCombo);
        flex3.setWidget(1, 0, new Label(cons.label_programname()));
        flex3.setWidget(1, 1, programCB.progCombo);                
        flex3.setWidget(1, 2, new Label(cons.category()));
        flex3.setWidget(1, 3, category_obj1.categoryCB);
        flex3.setWidget(2, 0, new Label(cons.agelimit()));
        flex3.setWidget(2, 1, ageNF1);
        FlexTable btnTable = new FlexTable();
        btnTable.setWidget(0, 0,submitButton);
        btnTable.setWidget(0, 1, cancelButton);
        flex3.setWidget(3, 0, btnTable);
        
        mainForm.setLabelAlign(Position.TOP);
        mainForm.setTitle(cons.heading_programAgeElig());
        mainForm.setPaddings(5);
        mainForm.setFrame(true);       
        mainForm.add(flex3);
        vp.add(mainForm);
        entityTypeCB.entityDescCB.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    entityNameCB.entityCombo.clearValue();
                    entityNameCB.entityCombo.disable();
                    programCB.progCombo.clearValue();
                    programCB.progCombo.disable();                                        
                    String entity_type = entityTypeCB.entityDescCB.getValue();
                    connectTemp.Entity_Name(creatorid, entity_type,
                        new AsyncCallback<CM_entityInfoGetter[]>() {
                            public void onFailure(Throwable caught) {
                                MessageBox.alert(cons.dbError(),
                                    caught.getMessage());
                            }

                            public void onSuccess(CM_entityInfoGetter[] arg0) {
                                RecordDef recordDef = new RecordDef(new FieldDef[] {
                                            new StringFieldDef("EntityName"),
                                            new StringFieldDef("Entitycode")
                                        });
                                final String[][] object2;
                                object2 = new String[arg0.length][2];
                                String str = null;
                                try {
                                    for (int i = 0; i < arg0.length; i++) {
                                        for (int k = 0; k < 2; k++) {
                                            if (k == 0) {
                                                str = arg0[i].getEntity_name();
                                            } else if (k == 1) {
                                                str = arg0[i].getEntity_id();
                                            }
                                            object2[i][k] = str;
                                        }
                                    }
                                } catch (Exception e2) {
                                    System.out.println("e2     " + e2);
                                }
                                Object[][] data = object2;
                                MemoryProxy proxy = new MemoryProxy(data);
                                ArrayReader reader = new ArrayReader(recordDef);
                                Store store = new Store(proxy, reader);
                                store.load();
                                entityNameCB.entityCombo.setStore(store);
                                entityNameCB.entityCombo.enable();
                            }
                        });
                }
            });

        entityNameCB.entityCombo.addListener(new ComboBoxListenerAdapter() {
                public void onSelect(ComboBox comboBox, Record record, int index) {
                    programCB.progCombo.clearValue();
                    programCB.progCombo.disable();                    
                    if (index >= 0) {
                        entity = entityNameCB.entityCombo.getRawValue();
                        entity_id = entityNameCB.entityCombo.getValue();
                        int counter=1;
                        connectTemp.getProgrammeOff(entity_id,counter,
                            new AsyncCallback<CM_ProgramInfoGetter[]>() {
                                public void onFailure(Throwable caught) {
                                    MessageBox.alert(cons.dbError(),
                                        caught.getMessage());
                                }

                                public void onSuccess(
                                    CM_ProgramInfoGetter[] arg0) {
                                    RecordDef recordDef = new RecordDef(new FieldDef[] {
                                                new StringFieldDef("Prog"),
                                                new StringFieldDef("ProgCode")
                                            });

                                    if (arg0.length > 0) {
                                        final String[][] object2;
                                        object2 = new String[arg0.length][2];
                                        String str = null;
                                        try {
                                            for (int i = 0; i < arg0.length;
                                                    i++) {
                                                for (int k = 0; k < 2; k++) {
                                                    if (k == 0) {
                                                        str = arg0[i].getProgram_name();
                                                    } else if (k == 1) {
                                                        str = arg0[i].getProgram_id();
                                                    }

                                                    object2[i][k] = str;
                                                }
                                            }
                                        } catch (Exception e2) {
                                            System.out.println("e2     " + e2);
                                        }
                                        Object[][] data = object2;
                                        MemoryProxy proxy = new MemoryProxy(data);
                                        ArrayReader reader = new ArrayReader(recordDef);
                                        Store store = new Store(proxy, reader);
                                        store.load();
                                        programCB.progCombo.setStore(store);
                                        programCB.progCombo.enable();
                                    } else {
                                        MessageBox.alert(msg.emptyComboBox( cons.label_programname(), entity));
                                    }
                                }
                            });
                    }
                }
            });
             
        submitButton.addListener(new ButtonListenerAdapter() {
                public void onClick(Button button, EventObject e) {
                    int check = 0;
                    check = markIV(entityTypeCB.entityDescCB) +
                        markIV(entityNameCB.entityCombo) +
                        markIV(programCB.progCombo) +                        
                        markIV(category_obj1.categoryCB) + markIV(ageNF1);
                    if (check == 0) {
                        final String[] details = new String[5];
                        details[0] = entity_id;
                        details[1] = programCB.progCombo.getValue();
                        details[2] = category_obj1.categoryCB.getValue();
                        details[3] = ageNF1.getRawValue();
                        details[4] = creatorid;                                                                                                
                        sendAgeDetails(details);
                    } else {
                        MessageBox.alert(msg.fieldsReqd());
                    }
                }
            });

        cancelButton.addListener(new ButtonListenerAdapter() {
            public void onClick(Button button, EventObject e) {
            	vp.clear();
            }
        });
        return vp;
    }

    public void sendAgeDetails(String[] details) {
        if (checkTf(ageNF1) == 0) {
            connectTemp.programEligibility(details,
                new AsyncCallback<Integer>() {
                    public void onFailure(Throwable arg0) {
                        MessageBox.alert(arg0.getMessage());
                    }

                    public void onSuccess(Integer arg0) {
                        if (arg0 > 0) {
                            MessageBox.alert(msg.duplicateEntry()); 
                            onModuleLoad();
                        } else {
                            MessageBox.show(new MessageBoxConfig() {

                                    {
                                        setMsg(msg.successfullySet( cons.agelimit()));
                                        setButtons(MessageBox.OK);
                                        setCallback(new MessageBox.PromptCallback() {
                                                public void execute(
                                                    String btnID, String text) {
                                                    try {
                                                        onModuleLoad();
                                                    } catch (Exception e) {
                                                    }
                                                }
                                            });
                                    }
                                });
                        }
                    }
                });
        } else {
            MessageBox.alert(msg.checkFields());
        }
    }

    public int checkTf(NumberField nf) {
        int check = 0;

        try {
            nf.validate();
        } catch (Exception e) {
            check++;
        }

        return check;
    }

    public int markIV(TextField t) {
        int check = 0;

        if ((valid.nullValidator(t.getValueAsString()) == true)) {
            try {
                check++;
                t.markInvalid("");
            } catch (Exception e) {
            }
        }

        return check;
    }
}
