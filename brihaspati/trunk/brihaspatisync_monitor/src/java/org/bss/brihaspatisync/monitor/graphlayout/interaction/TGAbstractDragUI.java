/* Original source which is modified resulting in this file, is copyrighted (c)
 * 2001-2002 by Alexander Shapiro in made available under the license as
 * reproduced below.
 *
 * TouchGraph LLC. Apache-Style Software License
 *
 *
 * Copyright (c) 2001-2002 Alexander Shapiro. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        TouchGraph LLC (http://www.touchgraph.com/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "TouchGraph" or "TouchGraph LLC" must not be used to endorse
 *    or promote products derived from this software without prior written
 *    permission.  For written permission, please contact
 *    alex@touchgraph.com
 *
 * 5. Products derived from this software may not be called "TouchGraph",
 *    nor may "TouchGraph" appear in their name, without prior written
 *    permission of alex@touchgraph.com.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL TOUCHGRAPH OR ITS CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 * 
 * The changes done in the original source to suit our requirement are released
 * under the license as given in LICENSE file distributed with this source.
 * All such changes are Copyrighted (c) 2011-2012 ETRG, IIT Kanpur.
 */

package org.bss.brihaspatisync.monitor.graphlayout.interaction;

/**
 *  TGAbstractDragUI.java
 *  */


import  org.bss.brihaspatisync.monitor.graphlayout.*;

import  java.awt.*;
import  java.awt.event.*;

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  @date 05/12/2011
 *      
 *  */


public abstract class TGAbstractDragUI extends TGSelfDeactivatingUI {

    public TGPanel tgPanel;

    private ADUIMouseListener ml;
    private ADUIMouseMotionListener mml;
    public boolean mouseWasDragged; //To differentiate between mouse pressed+dragged, and mouseClicked

  // ............

   /** Constructor with TGPanel <tt>tgp</tt>.
     */
    public TGAbstractDragUI(TGPanel tgp) {
        tgPanel=tgp;
        ml =new ADUIMouseListener();
        mml=new ADUIMouseMotionListener();
    }

     public final void activate() {
        preActivate();
        tgPanel.addMouseListener(ml);
        tgPanel.addMouseMotionListener(mml);
			 mouseWasDragged=false;
     }

    public final void activate(MouseEvent e) {
        activate();
        mousePressed(e);
    }

    public final void deactivate() {
        preDeactivate();
        tgPanel.removeMouseListener(ml);
        tgPanel.removeMouseMotionListener(mml);
        super.deactivate(); //To activate parentUI from TGUserInterface
    }

    public abstract void preActivate();
    public abstract void preDeactivate();

    public abstract void mousePressed( MouseEvent e );
    public abstract void mouseDragged( MouseEvent e );
    public abstract void mouseReleased( MouseEvent e );


    private class ADUIMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            TGAbstractDragUI.this.mousePressed(e);
        }

        public void mouseReleased(MouseEvent e) {
            TGAbstractDragUI.this.mouseReleased(e);
            if (selfDeactivate) deactivate();
        }
    }

    private class ADUIMouseMotionListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            mouseWasDragged=true;
              TGAbstractDragUI.this.mouseDragged(e);
        }
    }

} // end com.touchgraph.graphlayout.interaction.TGAbstractDragUI
