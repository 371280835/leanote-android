package com.leanote.android.model;


import android.text.TextUtils;

import com.leanote.android.Leanote;
import com.leanote.android.datasets.AccountTable;


/**
 * The app supports only one WordPress.com account at the moment, so we might use getDefaultAccount() everywhere we
 * need the account data.
 */
public class AccountHelper {
    private static Account sAccount;

    public static Account getDefaultAccount() {
        if (sAccount == null) {
            sAccount = AccountTable.getDefaultAccount();
            if (sAccount == null) {
                sAccount = new Account();
            }
        }
        return sAccount;
    }

    public static boolean isSignedIn() {
        return getDefaultAccount().hasAccessToken() || (Leanote.leaDB.getNumVisibleBlogs() != 0);
    }

    public static boolean isSignedInWordPressDotCom() {
        return getDefaultAccount().hasAccessToken();
    }

    public static boolean isJetPackUser() {
        return Leanote.leaDB.hasAnyJetpackBlogs();
    }

    public static String getCurrentUsernameForBlog(Blog blog) {
        if (!TextUtils.isEmpty(getDefaultAccount().getUserName())) {
            return getDefaultAccount().getUserName();
        } else if (blog != null) {
            return blog.getUsername();
        }
        return "";
    }
}
