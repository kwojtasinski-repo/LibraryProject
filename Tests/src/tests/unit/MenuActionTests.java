package tests.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import entities.MenuAction;
import services.MenuActionService;

public class MenuActionTests {

	private MenuActionService menuActionService;
	
	public MenuActionTests() {
		menuActionService = new MenuActionService();
	}
	
	@Test
	public void given_valid_id_should_return_author() {
		String menu = "Main";
		Integer expectedSize = 17;
		
		List<MenuAction> menuList = menuActionService.getMenuActionByMenuName(menu);
		
		assertThat(menuList.size()).isEqualTo(expectedSize);
	}
}