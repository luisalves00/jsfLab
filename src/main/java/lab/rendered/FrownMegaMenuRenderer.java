package lab.rendered;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import lab.view.model.SubmenuItem;

import org.primefaces.component.column.Column;
import org.primefaces.component.megamenu.MegaMenu;
import org.primefaces.component.megamenu.MegaMenuRenderer;
import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.menu.Menu;
import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.separator.Separator;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.util.ComponentUtils;

public class FrownMegaMenuRenderer extends MegaMenuRenderer {

	public FrownMegaMenuRenderer() {
		super();
	}

	@Override
	protected void encodeRootItems(FacesContext context, MegaMenu menu)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		for (Iterator<UIComponent> iterator = menu.getChildren().iterator(); iterator
				.hasNext();) {
			UIComponent child = (UIComponent) iterator.next();

			if (child.isRendered()) {
				if (child instanceof MenuItem) {
					writer.startElement("li", null);
					writer.writeAttribute("class", Menu.MENUITEM_CLASS, null);
					writer.writeAttribute("role", "menuitem", null);
					encodeMenuItem(context, (MenuItem) child);
					writer.endElement("li");
				} else if (child instanceof SubmenuItem) {
					// clicable submenus
					encodeRootSubmenuItem(context, (SubmenuItem) child);
				} else if (child instanceof Submenu) {
					encodeRootSubmenu(context, (Submenu) child);
				} else if (child instanceof Separator) {
					encodeSeparator(context, (Separator) child);
				}
			}
		}
	}

	@Override
	protected void encodeColumn(FacesContext context, Column column)
			throws IOException {
		ResponseWriter writer = context.getResponseWriter();

		writer.startElement("td", null);
		if (column.getStyle() != null)
			writer.writeAttribute("style", column.getStyle(), null);
		if (column.getStyleClass() != null)
			writer.writeAttribute("class", column.getStyleClass(), null);

		for (Iterator<UIComponent> iterator = column.getChildren().iterator(); iterator
				.hasNext();) {
			UIComponent child = (UIComponent) iterator.next();

			if (child.isRendered()) {
				if (child instanceof SubmenuItem) {
					// clicable submenus
					encodeDescendantSubmenuItem(context, (SubmenuItem) child);
				}
				if (child instanceof Submenu) {
					encodeDescendantSubmenu(context, (Submenu) child);
				} else if (child instanceof Separator) {
					encodeSubmenuSeparator(context, (Separator) child);
				} else {
					child.encodeAll(context);
				}
			}
		}

		writer.endElement("td");
	}

	protected void encodeDescendantSubmenuItem(FacesContext context, SubmenuItem submenuItem) throws IOException {
		String clientId = submenuItem.getClientId(context);
		ResponseWriter writer = context.getResponseWriter();
		String icon = submenuItem.getIcon();
		String title = submenuItem.getTitle();

		if (submenuItem.shouldRenderChildren()) {
			renderChildren(context, submenuItem);
		} else {
			boolean disabled = submenuItem.isDisabled();
			String onclick = submenuItem.getOnclick();

			writer.startElement("a", null);
			writer.writeAttribute("id", submenuItem.getClientId(context), null);
			if (title != null) {
				writer.writeAttribute("title", title, null);
			}

			String styleClass = submenuItem.getStyleClass();
			styleClass = styleClass == null ? AbstractMenu.MENUITEM_LINK_CLASS
					: AbstractMenu.MENUITEM_LINK_CLASS + " " + styleClass;
			styleClass = disabled ? styleClass + " ui-state-disabled"
					: styleClass;

			writer.writeAttribute("class", styleClass, null);

			if (submenuItem.getStyle() != null) {
				writer.writeAttribute("style", submenuItem.getStyle(), null);
			}

			// GET
			if (submenuItem.getUrl() != null
					|| submenuItem.getOutcome() != null) {
				String targetURL = getTargetURL(context, submenuItem);
				String href = disabled ? "javascript:void(0)" : targetURL;
				writer.writeAttribute("href", href, null);

				if (submenuItem.getTarget() != null) {
					writer.writeAttribute("target", submenuItem.getTarget(),
							null);
				}
			}
			// POST
			else {
				writer.writeAttribute("href", "javascript:void(0)", null);

				UIComponent form = ComponentUtils.findParentForm(context,
						submenuItem);
				if (form == null) {
					throw new FacesException(
							"MenuItem must be inside a form element");
				}

				String command = submenuItem.isAjax() ? buildAjaxRequest(
						context, submenuItem, form) : buildNonAjaxRequest(
						context, submenuItem, form, clientId, true);

				onclick = onclick == null ? command : onclick + ";" + command;
			}

			if (onclick != null && !disabled) {
				writer.writeAttribute("onclick", onclick, null);
			}

			if (icon != null) {
				writer.startElement("span", null);
				writer.writeAttribute("class", AbstractMenu.MENUITEM_ICON_CLASS
						+ " " + icon, null);
				writer.endElement("span");
			}

			if (submenuItem.getValue() != null) {
				writer.startElement("span", null);
				writer.writeAttribute("class",
						AbstractMenu.MENUITEM_TEXT_CLASS, null);
				writer.writeText((String) submenuItem.getValue(), "value");
				writer.endElement("span");
			}

			writer.endElement("a");
		}

        //menuitems
        for(Iterator<UIComponent> iterator = submenuItem.getChildren().iterator(); iterator.hasNext();) {
            UIComponent child = (UIComponent) iterator.next();

            if(child.isRendered()) {
                if(child instanceof MenuItem) {
                    writer.startElement("li", null);
                    writer.writeAttribute("class", Menu.MENUITEM_CLASS, null);
                    writer.writeAttribute("role", "menuitem", null);
                    encodeMenuItem(context, (MenuItem) child);
                    writer.endElement("li");
                } 
                else if(child instanceof Separator) {
                    encodeSeparator(context, (Separator) child);
                }
            }
        }
        
        writer.endElement("ul");
    }
	
	protected void encodeRootSubmenuItem(FacesContext context,
			SubmenuItem submenuItem) throws IOException {
		String clientId = submenuItem.getClientId(context);
		ResponseWriter writer = context.getResponseWriter();
		String icon = submenuItem.getIcon();
		String title = submenuItem.getTitle();

		if (submenuItem.shouldRenderChildren()) {
			renderChildren(context, submenuItem);
		} else {
			boolean disabled = submenuItem.isDisabled();
			String onclick = submenuItem.getOnclick();

			writer.startElement("a", null);
			writer.writeAttribute("id", submenuItem.getClientId(context), null);
			if (title != null) {
				writer.writeAttribute("title", title, null);
			}

			String styleClass = submenuItem.getStyleClass();
			styleClass = styleClass == null ? AbstractMenu.MENUITEM_LINK_CLASS
					: AbstractMenu.MENUITEM_LINK_CLASS + " " + styleClass;
			styleClass = disabled ? styleClass + " ui-state-disabled"
					: styleClass;

			writer.writeAttribute("class", styleClass, null);

			if (submenuItem.getStyle() != null) {
				writer.writeAttribute("style", submenuItem.getStyle(), null);
			}

			// GET
			if (submenuItem.getUrl() != null
					|| submenuItem.getOutcome() != null) {
				String targetURL = getTargetURL(context, submenuItem);
				String href = disabled ? "javascript:void(0)" : targetURL;
				writer.writeAttribute("href", href, null);

				if (submenuItem.getTarget() != null) {
					writer.writeAttribute("target", submenuItem.getTarget(),
							null);
				}
			}
			// POST
			else {
				writer.writeAttribute("href", "javascript:void(0)", null);

				UIComponent form = ComponentUtils.findParentForm(context,
						submenuItem);
				if (form == null) {
					throw new FacesException(
							"MenuItem must be inside a form element");
				}

				String command = submenuItem.isAjax() ? buildAjaxRequest(
						context, submenuItem, form) : buildNonAjaxRequest(
						context, submenuItem, form, clientId, true);

				onclick = onclick == null ? command : onclick + ";" + command;
			}

			if (onclick != null && !disabled) {
				writer.writeAttribute("onclick", onclick, null);
			}

			if (icon != null) {
				writer.startElement("span", null);
				writer.writeAttribute("class", AbstractMenu.MENUITEM_ICON_CLASS
						+ " " + icon, null);
				writer.endElement("span");
			}

			if (submenuItem.getValue() != null) {
				writer.startElement("span", null);
				writer.writeAttribute("class",
						AbstractMenu.MENUITEM_TEXT_CLASS, null);
				writer.writeText((String) submenuItem.getValue(), "value");
				writer.endElement("span");
			}

			writer.endElement("a");
		}

		// submenus
		if (submenuItem.getChildCount() > 0) {
			writer.startElement("ul", null);
			writer.writeAttribute("class", Menu.TIERED_CHILD_SUBMENU_CLASS,
					null);
			writer.writeAttribute("role", "menu", null);

			writer.startElement("table", null);
			writer.startElement("tbody", null);
			writer.startElement("tr", null);

			for (Iterator<UIComponent> iterator = submenuItem.getChildren()
					.iterator(); iterator.hasNext();) {
				UIComponent child = (UIComponent) iterator.next();

				if (child.isRendered() && child instanceof Column) {
					encodeColumn(context, (Column) child);
				}
			}

			writer.endElement("tr");
			writer.endElement("tbody");
			writer.endElement("table");

			writer.endElement("ul");
		}

		writer.endElement("li");
	}

}
