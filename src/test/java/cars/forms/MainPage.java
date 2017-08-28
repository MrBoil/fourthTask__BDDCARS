package cars.forms;

import cars.menu.MainPageTopMenu;
import cars.menu.SearchMenu;

public class MainPage {

    public SearchMenu navigateSearchMenu(){
        return new SearchMenu();
    }

    public MainPageTopMenu navigateMainPageTopMenu(){
        return new MainPageTopMenu();
    }
}
