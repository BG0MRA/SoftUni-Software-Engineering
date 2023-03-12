package E04InterfacesAndAbstraction.P06_MilitaryElite.Interface;



import E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers.Repair;

import java.util.Collection;

public interface Engineer {

    void addRepair(Repair repair);

    Collection<Repair> getRepairs();
}
