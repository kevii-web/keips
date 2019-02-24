package com.keviiweb.keips;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Student class to represent how OSA points are represented and calculated.
 */

public class OSAPoints {
    private static final int CCA_CATEGORY_CAP = 45;
    private static final int MAX_CCA_CATEGORY = 3;
    private static final int TOTAL_CCA_USED_FOR_POINTS = 4;
    private int totalOsaPoints;
    private boolean haveContrasting;
    List<CCA> ccaList;
    
    public OSAPoints (List<CCA> ccaList) {
        this.ccaList = ccaList;
        totalOsaPoints = 0;
    }
    
    public int calculate() {
        Collections.sort(ccaList, new SortTotalPoints());
        int currentCcaAdded = 0;
        int numAdmin = 0;
        int numCulture = 0;
        int numSports = 0;
        
        if(haveContrasting()) {
            this.haveContrasting = true;
            List<CCA> modifiedList = modifyList();
            Collections.sort(modifiedList, new SortTotalPoints());
            
            for (int k = 0; k < modifiedList.size(); k++) {
                if(currentCcaAdded == TOTAL_CCA_USED_FOR_POINTS) {
                    return totalOsaPoints;
                }
                CCA currentCCA = modifiedList.get(k);
                String currentCategory = currentCCA.getCategory();
                
                if(currentCategory.equals("Admin")) {
                    if(numAdmin == MAX_CCA_CATEGORY) {
                        continue;
                    } else {
                        totalOsaPoints += currentCCA.getTotalPoints();
                        numAdmin++;
                        currentCcaAdded++;
                    }
                } else if(currentCategory.equals("Culture")) {
                    if(numCulture == MAX_CCA_CATEGORY) {
                        continue;
                    } else {
                        totalOsaPoints += currentCCA.getTotalPoints();
                        numCulture++;
                        currentCcaAdded++;
                    }
                } else {
                    if(numSports == MAX_CCA_CATEGORY) {
                        continue;
                    } else {
                        totalOsaPoints += currentCCA.getTotalPoints();
                        numSports++;
                        currentCcaAdded++;
                    }
                }
            }
        } else {
            this.haveContrasting = false;
            for(int j = 0; j < TOTAL_CCA_USED_FOR_POINTS; j++) {
                totalOsaPoints += ccaList.get(j).getTotalPoints();
            }
            if(totalOsaPoints > CCA_CATEGORY_CAP) {
                return CCA_CATEGORY_CAP;
            } else {
                return totalOsaPoints;
            }
        }
        return totalOsaPoints;
    }

    /**
     * This method check is the CCAs in the list are all of the same
     * type and if there exists any contrasting CCA
     * @return true if any contrasting CCA, false otherwise.
     */
    public boolean haveContrasting() {
        int adminCCAs = 0;
        int cultureCCAs = 0;
        int sportsCCAs = 0;
        
        for (int i = 0; i < ccaList.size(); i++) {
            String category = ccaList.get(i).getCategory();
            if(category.equals("Admin")) {
                adminCCAs++;
            } else if (category.equals("Culture")) {
                cultureCCAs++;
            } else if(category.equals("Sports")) {
                sportsCCAs++;
            }
        }
        int totalCCA = ccaList.size();
        if((totalCCA == adminCCAs) || (totalCCA == cultureCCAs) || (totalCCA == sportsCCAs)) {
            return false;
        }
        return true;
    }

    /**
     * Method used to get effective list of CCA points since each CCA category can only have a max of 45 points
     * @return modified list
     */
    public ArrayList<CCA> modifyList () {
        ArrayList<CCA> modifiedList = new ArrayList<>(ccaList);
        int currentAdmin = 0;
        int currentCulture = 0;
        int currentSports = 0;
        String categoryCCA;
        CCA cca;
        
        for(int i = 0; i < ccaList.size(); i++) {
            cca = modifiedList.get(i);
            categoryCCA = cca.getCategory();
            if(categoryCCA.equals("Admin")) {
                if(currentAdmin + cca.getTotalPoints() > CCA_CATEGORY_CAP) {
                    cca.setTotalPoints(CCA_CATEGORY_CAP - currentAdmin);
                } else {
                    currentAdmin += cca.getTotalPoints();
                }
            } else if(categoryCCA.equals("Culture")) {
                if(currentCulture + cca.getTotalPoints() > CCA_CATEGORY_CAP) {
                    cca.setTotalPoints(CCA_CATEGORY_CAP - currentCulture);
                } else {
                    currentCulture += cca.getTotalPoints();
                }
            } else {
                if(currentSports + cca.getTotalPoints() > CCA_CATEGORY_CAP) {
                    cca.setTotalPoints(CCA_CATEGORY_CAP - currentSports);
                } else {
                    currentSports += cca.getTotalPoints();
                }
            }
        }
        return modifiedList;
    }
}
