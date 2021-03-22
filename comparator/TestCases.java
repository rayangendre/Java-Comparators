import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.*;

import org.junit.Test;
import org.junit.Before;

public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArrayList songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Queen", "Bohemian Rhapsody", 1975),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
              );
      songList.sort(new ArtistComparator());
      assertEquals(songList,expectedList);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> titleComparator = (Song one, Song two) ->{
         return one.getTitle().compareTo(two.getTitle());
      };
      ArrayList songList = new ArrayList<>(Arrays.asList(songs));

      int num1 = titleComparator.compare(((Song)songList.get(0)), ((Song)songList.get(1)));
      assertTrue(num1> 0);
      int num2 = titleComparator.compare(((Song)songList.get(1)), ((Song)songList.get(2)));
      assertTrue(num2 < 0);
   }

   @Test
   public void testYearExtractorComparator()
   {
      ArrayList songList = new ArrayList<>(Arrays.asList(songs));
      Comparator.comparing(Song::getYear);
      songList.sort(Comparator.comparing(Song::getYear, Comparator.reverseOrder()));
      List<Song> expectedList = Arrays.asList(
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005),
              new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Gerry Rafferty", "Baker Street", 1998),
              new Song("Foo Fighters", "Baker Street", 1997),
              new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Queen", "Bohemian Rhapsody", 1975)
      );
      assertEquals(songList, expectedList);

   }

   @Test
   public void testComposedComparator()
   {
      Comparator<Song> artComp = (Song one, Song two) ->{
         return one.getArtist().compareTo(two.getArtist());
      };

      Comparator<Song> titleComp = (Song one, Song two) ->{
         return one.getTitle().compareTo(two.getTitle());
      };

      ComposedComparator composedComparator = new ComposedComparator(titleComp, artComp);
      Song one = songs[3];
      Song two = songs[5];
      assertTrue(composedComparator.compare(one, two) > 0);
   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> comparator = Comparator.comparing(Song::getTitle).thenComparing(Song::getArtist);
      int num = comparator.compare(songs[3],songs[5]);
      assertTrue(num > 0);
   }

   @Test
   public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );
      Comparator comparator = Comparator.comparing(Song::getArtist).thenComparing(Song::getTitle).thenComparing(Song::getYear);

      songList.sort(comparator);

      assertEquals(songList, expectedList);
   }
}
