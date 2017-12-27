package io.ezorrio.md5;

import java.util.prefs.Preferences;

public class AppPrefs {
    private Preferences mPrefs;
    private static final String PREF_LENGTH = "pref_length";
    private static final int DEF_LENGTH = 8;

    private static final String PREF_HAS_DIGIT = "pref_digit";
    private static final boolean DEF_HAS_DIGIT = false;

    private static final String PREF_HAS_UPPERCASE = "pref_uppercase";
    private static final boolean DEF_HAS_UPPERCASE = true;

    private static final String PREF_HAS_LOWERCASE = "pref_lowercase";
    private static final boolean DEF_HAS_LOWERCASE = true;

    public AppPrefs() {
        mPrefs = Preferences.userRoot().node("prefs");
    }

    public void setMinLength(int length) {
        mPrefs.putInt(PREF_LENGTH, length);
    }

    public int getMinLength() {
        return mPrefs.getInt(PREF_LENGTH, DEF_LENGTH);
    }

    public void setHasUpperCase(boolean value) {
        mPrefs.putBoolean(PREF_HAS_UPPERCASE, value);
    }

    public boolean hasUpperCase() {
        return mPrefs.getBoolean(PREF_HAS_UPPERCASE, DEF_HAS_UPPERCASE);
    }

    public void setHasLowerCase(boolean value) {
        mPrefs.putBoolean(PREF_HAS_LOWERCASE, value);
    }

    public boolean hasLowerCase() {
        return mPrefs.getBoolean(PREF_HAS_LOWERCASE, DEF_HAS_LOWERCASE);
    }

    public void setHasDigit(boolean value) {
        mPrefs.putBoolean(PREF_HAS_DIGIT, value);
    }

    public boolean hasDigit() {
        return mPrefs.getBoolean(PREF_HAS_DIGIT, DEF_HAS_DIGIT);
    }
}
