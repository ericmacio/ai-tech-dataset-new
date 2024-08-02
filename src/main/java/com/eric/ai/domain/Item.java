package com.eric.ai.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Item {

    private String name;
    private String acronym;
    private List<Instance> instances;
    private final List<String> parents = new ArrayList<>();

    public Item() {
    }

    public Item(String name, String acronym, List<Instance> instances) {
        this.name = name;
        this.acronym = acronym;
        this.instances = instances;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instance> getInstances() {
        return instances;
    }

    public void setInstances(List<Instance> instances) {
        this.instances = instances;
    }

    public void setParents(List<String> parentsName) {
        parents.addAll(parentsName);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                ", instances=" + instances +
                ", parents=" + parents +
                '}';
    }

    public void display() {
        StringBuilder parentsStr = new StringBuilder();
        this.parents.forEach(p -> parentsStr.append("/").append(p));
        System.out.println("[" + parentsStr + "] " + this.name);
    }

    public Boolean instancesContainsWordIgnoreCase(String keyWord) {
        return this.instances.stream()
                .map(Instance::getProducts)
                .flatMap(Collection::stream)
                .anyMatch(s -> s.toLowerCase().contains(keyWord.toLowerCase()));
    }

    public boolean containsInstancesForProvider(String provider) {
        return this.instances.stream()
                .map(Instance::getProvider)
                .anyMatch(s -> s.toLowerCase().contains(provider.toLowerCase()));
    }

}
