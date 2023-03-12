package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;



import E04InterfacesAndAbstraction.P06_MilitaryElite.Interface.LieutenantGeneral;

import java.util.*;
import java.util.stream.Collectors;

public class LieutenantGeneralImpl extends PrivateImpl implements LieutenantGeneral {

    private List<PrivateImpl> privates;

    public LieutenantGeneralImpl(int id, String firstName, String lastName, double salary) {
        super(id, firstName, lastName, salary);
        this.privates = new ArrayList<>();
    }

    @Override
    public void addPrivate(PrivateImpl priv) {
        this.privates.add(priv);
    }

//    @Override
//    public List<PrivateImpl> getPrivates() {
////        return Collections.unmodifiableList(privates.values().stream().toList());
//        return privates.values().stream()
//                .collect(Collectors.toUnmodifiableList());
//    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Privates:");
        if (!privates.isEmpty()) {
            out.append(System.lineSeparator())
                    .append(privates.stream()
                            .sorted(Comparator.comparingInt(PrivateImpl::getId).reversed())
                            .map(p -> "  " + p)
                            .collect(Collectors.joining(System.lineSeparator())));
        }

        return out.toString();
    }
}
