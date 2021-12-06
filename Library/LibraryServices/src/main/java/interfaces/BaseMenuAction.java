package interfaces;

import java.util.List;

import entities.MenuAction;

public interface BaseMenuAction <T extends MenuAction> {
	List<T> getMenuActionByMenuName(String menuName);
}