package lab.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lab.view.model.SubmenuItem;

import org.primefaces.component.column.Column;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;

@ManagedBean
@ViewScoped
public class MainController implements Serializable {

	private static final long serialVersionUID = 3973801993975443027L;

	private MenuModel model;

	@PostConstruct
    public void init() {
        model = new DefaultMenuModel();
        
        //Menu 1
        Submenu menu1 = new Submenu();
        menu1.setLabel("Menu 1");
        
        //Column 1 from Menu 1
        Column menu1c1 = new Column();
        menu1.getChildren().add(menu1c1);
        
        //Menu 1  submenu 1.1
        Submenu menu11 = new Submenu();
        menu11.setLabel("Submenu 1.1");
        menu1c1.getChildren().add(menu11);
        
        //3 Menu items on first column
        MenuItem item = new MenuItem();
        item.setValue("Topics 1.1.1");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu11.getChildren().add(item);
        
        item = new MenuItem();
        item.setValue("External 1.1.2");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu11.getChildren().add(item);
        
        item = new MenuItem();
        item.setValue("Help 1.1.3");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu11.getChildren().add(item);
        
        
        
        //Column 2 from Menu 1
        Column menu1c2 = new Column();
        menu1.getChildren().add(menu1c2);
        
        //Menu 1 submenu 1.2
        Submenu menu12 = new Submenu();
        menu12.setLabel("Submenu 1.2");
        menu1c2.getChildren().add(menu12);
         
        item = new MenuItem();
        item.setValue("Titles 1.2.1");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
        
        item = new MenuItem();
        item.setValue("Things 1.2.2");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
        
        Separator sp  = new Separator();
        menu12.getChildren().add(sp);
        
        item = new MenuItem();
        item.setValue("Extra 1.2.3");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
         

        //Menu 1 submenu 1.3
        SubmenuItem menu13 = new SubmenuItem();
        menu13.setValue("Submenu 1.3 Clicable");
        menu13.setUrl("http://www.primefaces.org");
        menu13.setIcon("ui-icon-home");
        menu1c2.getChildren().add(menu13);
         
        item = new MenuItem();
        item.setValue("Frown 1.3.1");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
        
        item = new MenuItem();
        item.setValue("Frabulous 1.3.2");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
        
        sp  = new Separator();
        menu12.getChildren().add(sp);
        
        item = new MenuItem();
        item.setValue("Extension 1.3.3");
        item.setUrl("http://www.primefaces.org");
        item.setIcon("ui-icon-home");
        menu12.getChildren().add(item);
        
        model.addSubmenu(menu1);
         
   
    }

	public MenuModel getModel() {
		return model;
	}

	

}