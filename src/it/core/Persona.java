package it.core;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    public interface NameChangedListener{
        void onNameChanged(String old, String newName);
    }

    private String nome;
    private String cognome;
    private List<NameChangedListener> listeners;

    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
        listeners = new ArrayList<>();
    }

    public void addListener(NameChangedListener nameChangedListener){
        listeners.add(nameChangedListener);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        listeners.forEach(l -> l.onNameChanged(this.nome, nome));
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public List<NameChangedListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<NameChangedListener> listeners) {
        this.listeners = listeners;
    }
}
