package com.javahonk.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "Court_Change")
public class CourtChange extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "open", nullable = false)
    private float open;


    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "close", nullable = false)
    private float close;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "haut", nullable = false)
    private float haut;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "bas", nullable = false)
    private float bas;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "volume_title", nullable = false)
    private int volume_title;

    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "volume_dt", nullable = false)
    private int volume_dt;


    @NotNull
    @Digits(integer = 8, fraction = 2)
    @Column(name = "variation", nullable = false)
    private float variation;

    public CourtChange() {

    }

    public CourtChange(String name, float open, float close, float haut, float bas, int volume_title, int volume_dt, float variation) {
        this.name = name;
        this.open = open;
        this.close = close;
        this.haut = haut;
        this.bas = bas;
        this.volume_title = volume_title;
        this.volume_dt = volume_dt;
        this.variation = variation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getHaut() {
        return haut;
    }

    public void setHaut(float haut) {
        this.haut = haut;
    }

    public float getBas() {
        return bas;
    }

    public void setBas(float bas) {
        this.bas = bas;
    }

    public int getVolume_title() {
        return volume_title;
    }

    public void setVolume_title(int volume_title) {
        this.volume_title = volume_title;
    }

    public int getVolume_dt() {
        return volume_dt;
    }

    public void setVolume_dt(int volume_dt) {
        this.volume_dt = volume_dt;
    }

    public float getVariation() {
        return variation;
    }

    public void setVariation(float variation) {
        this.variation = variation;
    }

    @Override
    public String toString() {
        return "CourtChange{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", haut=" + haut +
                ", bas=" + bas +
                ", volume_title=" + volume_title +
                ", volume_dt=" + volume_dt +
                ", variation=" + variation +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourtChange)) return false;

        CourtChange that = (CourtChange) o;

        if (id != that.id) return false;
        if (Float.compare(that.open, open) != 0) return false;
        if (Float.compare(that.close, close) != 0) return false;
        if (Float.compare(that.haut, haut) != 0) return false;
        if (Float.compare(that.bas, bas) != 0) return false;
        if (volume_title != that.volume_title) return false;
        if (volume_dt != that.volume_dt) return false;
        if (variation != that.variation) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (open != +0.0f ? Float.floatToIntBits(open) : 0);
        result = 31 * result + (close != +0.0f ? Float.floatToIntBits(close) : 0);
        result = 31 * result + (haut != +0.0f ? Float.floatToIntBits(haut) : 0);
        result = 31 * result + (bas != +0.0f ? Float.floatToIntBits(bas) : 0);
        result = 31 * result + volume_title;
        result = 31 * result + volume_dt;
        result = 31 * result + (variation != +0.0f ? Float.floatToIntBits(variation) : 0);
        return result;
    }
}
