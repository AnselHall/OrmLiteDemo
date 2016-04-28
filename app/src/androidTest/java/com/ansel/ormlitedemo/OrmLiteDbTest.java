package com.ansel.ormlitedemo;

import android.test.AndroidTestCase;
import android.util.Log;

import com.ansel.ormlitedemo.bean.User;
import com.ansel.ormlitedemo.dao.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 */
public class OrmLiteDbTest extends AndroidTestCase {

    public void testAddUser() {

        User u1 = new User("ansel", "hall");
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            helper.getUserDao().create(u1);
            u1 = new User("ansel_1", "hall");
            helper.getUserDao().create(u1);
            u1 = new User("ansel_2", "hall");
            helper.getUserDao().create(u1);
            u1 = new User("ansel_3", "hall");
            helper.getUserDao().create(u1);
            u1 = new User("ansel_4", "hall");
            helper.getUserDao().create(u1);
            u1 = new User("ansel_5", "hall");
            helper.getUserDao().create(u1);

//            testList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testDeleteUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            helper.getUserDao().deleteById(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testUpdateUser() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            User u1 = new User("ansel_andev", "hall");
            u1.setId(3);
            helper.getUserDao().update(u1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testList() {

        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());
        try {
            User u1 = new User("ansel_andev", "hall");
            u1.setId(2);
            List<User> users = helper.getUserDao().queryForAll();
            Log.e("TAG", users.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testDropTable() {
        DatabaseHelper helper = DatabaseHelper.getHelper(getContext());

        try {

            helper.getUserDao().delete(new User("ansel_andev", "hall"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
