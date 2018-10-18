package net.avdw.multimaze.protocol;

import com.google.inject.Inject;

import java.util.Set;

public class ProtocolCrudGenerator {
    private Set<String> resources;

    @Inject
    ProtocolCrudGenerator(Set<String> resources) {
        this.resources = resources;
    }

    public void generate() {
        initProtocol();
    }

    private void initProtocol() {
        resources.forEach(resource -> {

        });
    }
}
