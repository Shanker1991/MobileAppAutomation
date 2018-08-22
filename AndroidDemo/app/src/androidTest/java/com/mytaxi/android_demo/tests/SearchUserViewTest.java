package com.mytaxi.android_demo.tests;

import android.support.test.rule.ActivityTestRule;

import com.mytaxi.android_demo.BaseTest;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.android_demo.services.LoginService;
import com.mytaxi.android_demo.services.NavMenuService;
import com.mytaxi.android_demo.services.SearchResultService;
import com.mytaxi.android_demo.services.DriverSearchService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SearchUserViewTest extends BaseTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup() {
        if (!LoginService.isLoginActivity()) {
            NavMenuService.logout();
        }
        LoginService.loginAs(adminUser.getUsername(), adminUser.getSalt());
    }

    @After
    public void cleanUp() {
        DriverSearchService.clearSearchText();
        NavMenuService.logout();
    }

    @Test
    public void checkIfHintTextIsDisplayed() {
        DriverSearchService.checkHintText();
    }

    @Test
    public void searchDriverResult() {
        DriverSearchService.searchWith(searchKey.sa.name());
        DriverSearchService.selectUserFromAutoComplete(driver.getName(), mActivityRule.getActivity());
        SearchResultService.checkDriverDetails(driver.getName(), dateFormat.format(driver.getRegisteredDate()), driver.getLocation());
        SearchResultService.checkHeaderTitle();
        SearchResultService.backToSearchResult();
    }

    @Test
    public void callDriver() {
        DriverSearchService.searchWith(searchKey.sa.name());
        DriverSearchService.selectUserFromAutoComplete(driver.getName(), mActivityRule.getActivity());
        SearchResultService.checkDriverDetails(driver.getName(), dateFormat.format(driver.getRegisteredDate()), driver.getLocation());
        SearchResultService.callDriver();
        SearchResultService.bringToForeground(mActivityRule.getActivity());
    }
}
