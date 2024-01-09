package fr.umontpellier.polytech.ig.colocalternant.profile;

import fr.umontpellier.polytech.ig.colocalternant.user.UserFacade;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * Test class of the profile facade
 */
public class ProfileFacadeTest {
    /**
     * Test if getting the profiles of a user works same as getting the profiles of this user with each role
     */
    @Test
    public void profilesExist() {
        UserFacade userFacade = UserFacade.getInstance();
        ProfileFacade profileFacade = ProfileFacade.getInstance();
        try {
            userFacade.login("john.doe@test.com", "password");
            List<Profile> profiles = profileFacade.getOwnProfiles(userFacade.getCurrentUser().getId());
            List<Integer> profileIDs = new ArrayList<Integer>();
            for (Profile profile : profiles) {
                profileIDs.add(profile.getId());
            }
            List<Integer> expectedProfileIDs = new ArrayList<Integer>();
            List<EnumRole> allRoles = new ArrayList<>();
            allRoles.add(EnumRole.Tenant);
            allRoles.add(EnumRole.Owner);
            allRoles.add(EnumRole.Admin);
            for (int i = 0; i < allRoles.size(); i++) {
                expectedProfileIDs.add(profileFacade.getProfileIdWithRole(userFacade.getCurrentUser().getId(), allRoles.get(i)));
            }
            // for each role, we check if the profile is in the list of profiles or if its id is -1 (does not exist) don't test it
            for (int i = 0; i < 3; i++) {
                if (expectedProfileIDs.get(i) != -1) {
                    assertTrue(profileIDs.contains(expectedProfileIDs.get(i)));
                }
            }
        } catch (Exception exception) {
            fail("UserFacade: login: Exception thrown.");
        }


    }
}
