package com.jusethag.popularmovies;

import org.junit.Before;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowApplication;

/**
 * Created by JusethAg on 9/1/2016.
 */
public abstract class BaseTest {
    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        if (RuntimeEnvironment.application != null) {
            ShadowApplication shadowApplication = Shadows.shadowOf(RuntimeEnvironment.application);
            shadowApplication.grantPermissions("android.permission.INTERNET");
        }
    }
}
