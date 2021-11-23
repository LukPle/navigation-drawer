package com.example.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

/**
 * This activity creates a Navigation Drawer using a Toolbar and NavigationView.
 * The result is an easy to use menu for navigating through an app.
 * Interacting with the navigation sends the user to different Fragments.
 *
 * Layout File for the activity: activity_main.xml
 * Layout File for the menu: drawer_navigation.xml
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    /**
     * This method references the Toolbar, Drawer and NavigationView.
     * Furthermore the Toolbar gets set as the ActionBar.
     * The ActionBarToggle represents the behaviour of the menu icon on the top left.
     * It also displays the HomeFragment at the start of the app.
     * @param savedInstanceState is a standard parameter.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    /**
     * Method for handling the clicks on menu items.
     * A switch case checks which item was selected and calls the suited Fragment.
     * The selected Fragment should be displayed afterwards.
     * After the selection the drawer needs to be closed.
     * @param item is the MenuItem that the user clicked on.
     * @return true closes the method successfully.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment selectedFragment = null;

        switch(item.getItemId()) {

            case R.id.nav_profile:
                selectedFragment = new ProfileFragment();
                break;
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_add:
                selectedFragment = new AddFragment();
                break;
            case R.id.nav_trends:
                selectedFragment = new TrendsFragment();
                break;
            case R.id.nav_achievements:
                selectedFragment = new AchievementsFragment();
                break;
            case R.id.nav_social:
                selectedFragment = new SocialFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    /**
     * If the user clicks on back while the drawer is open, only the drawer should be closed.
     */
    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        }
        else {

            super.onBackPressed();
        }
    }

}