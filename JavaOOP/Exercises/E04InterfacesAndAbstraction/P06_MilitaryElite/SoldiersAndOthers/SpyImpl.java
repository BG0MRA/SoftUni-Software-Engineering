package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;


import E04InterfacesAndAbstraction.P06_MilitaryElite.Interface.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Code Number: ")
                .append(codeNumber);

        return out.toString();
    }
}
