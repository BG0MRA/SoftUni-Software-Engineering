package E04InterfacesAndAbstraction.P06_MilitaryElite.Interface;



import E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers.Mission;

import java.util.Collection;

public interface Commando {

    void addMission (Mission mission);

    Collection<Mission> getMissions();

}
