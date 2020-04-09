package iia.games.nim;

import iia.games.base.IMove;

import java.util.Objects;

public class MoveNim implements IMove {
    public static final int MAX_N = 3;
    public static final int MIN_N = 1;
    public int n;

    public MoveNim(int n){
        assert n <= MAX_N;
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveNim moveNim = (MoveNim) o;
        return n == moveNim.n;
    }

    @Override
    public int hashCode() {
        return n;
    }
}
