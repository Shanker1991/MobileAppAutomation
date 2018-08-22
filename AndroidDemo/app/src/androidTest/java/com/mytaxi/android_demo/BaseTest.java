package com.mytaxi.android_demo;

import com.mytaxi.android_demo.models.Driver;
import com.mytaxi.android_demo.models.User;

import org.junit.BeforeClass;;

import java.text.SimpleDateFormat;

public class BaseTest {
    protected static Driver driver;
    protected static User adminUser;
    protected static User invalidUser;
    protected static SimpleDateFormat dateFormat;
    protected static final String strDateFormat = "yyyy-MM-dd";

    protected static enum searchKey {
        sa,
        se
    }

    @BeforeClass
    public static void initData() throws Exception {
        dateFormat = new SimpleDateFormat(strDateFormat);
        driver = new Driver("Sarah Scott", "(413) 868-2228", "", "6834 charles st", dateFormat.parse("2002-10-18"));
        adminUser = new User("crazydog335", "venture", "");
        invalidUser = new User("crazydog", "vent", "");
    }
}