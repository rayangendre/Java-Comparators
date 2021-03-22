import java.util.Comparator;

public class ComposedComparator implements Comparator<Song> {
    Comparator<Song> c1;
    Comparator<Song> c2;

    public ComposedComparator(Comparator<Song> c1, Comparator<Song> c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public int compare(Song one, Song two) {
        int c1comparison = c1.compare(one, two);
        if (c1comparison == 0){
            return c2.compare(one, two);
        }else {
            return c1comparison;
        }

    }
}
