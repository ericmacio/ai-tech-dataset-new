package com.eric.ai.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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

    public void addParents(List<String> parentsName) {
        parents.addAll(parentsName);
    }

    public List<String> getParents() {
        return parents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
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

    public Stream<String> getDataStream(String separator, String categoryAcronym) {
        StringBuilder dataStr = new StringBuilder();
        dataStr.append(this.parents.get(1)).append(separator);
        if(categoryAcronym != null)
            dataStr.append(categoryAcronym);
        for(int i = 2; i < this.parents.size(); i++) {
            dataStr.append(separator).append(this.parents.get(i));
        }
        if(this.parents.size() < 3)
            dataStr.append(separator);
        dataStr.append(separator).append(this.name).append(separator);
        if(this.acronym != null)
            dataStr.append(this.acronym);
        return this.instances.stream()
                .flatMap(instance -> instance.getDataStream(separator, dataStr.toString()));
    }

    public Boolean instancesContainsWordIgnoreCase(String keyWord) {
        return this.instances.stream()
                .map(Instance::getProducts)
                .flatMap(Collection::stream)
                .anyMatch(s -> s.toLowerCase().contains(keyWord.toLowerCase()));
    }

    public Boolean containsInstancesForProvider(String provider) {
        return this.instances.stream()
                .map(Instance::getProvider)
                .anyMatch(s -> s.toLowerCase().contains(provider.toLowerCase()));
    }

    public Boolean parentsContainsWordIgnoreCase(String keyWord) {
        return this.parents.stream()
                .anyMatch(s -> s.toLowerCase().contains(keyWord.toLowerCase()));
    }

}
