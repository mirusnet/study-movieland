package com.mirus.movieland.repository.jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumSet;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortParameters {
    private String field;
    private SortDirection sortDirection;

    public enum SortDirection {
        ASC, DESC;

        public static SortDirection fromValue(String value) {
            return EnumSet.allOf(SortDirection.class)
                    .stream()
                    .filter(v -> v.name().equalsIgnoreCase(value))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unknown sort order: " + value));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortParameters that = (SortParameters) o;
        return Objects.equals(field, that.field) &&
                sortDirection == that.sortDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, sortDirection);
    }
}
