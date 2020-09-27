package com.spirituspoland.memogame.data;

import android.graphics.drawable.Drawable;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pokemon {
    private String name;
    private Drawable drawable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(name, pokemon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
