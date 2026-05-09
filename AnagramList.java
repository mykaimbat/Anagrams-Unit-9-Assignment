import java.util.ArrayList;

public class AnagramList
{
    private final ArrayList<String> anagrams;

    public AnagramList(String word)
    {
        anagrams = new ArrayList<String>();

        // Generate all anagrams
        completeAnagrams("", word);

        // Remove duplicates
        removeDupes();

        // Sort alphabetically
        sortAnagrams();
    }

    private void completeAnagrams(String start, String end)
    {
        // Base case
        if (end.length() == 0)
        {
            anagrams.add(start);
        }
        else
        {
            // Recursive case
            for (int i = 0; i < end.length(); i++)
            {
                String letter = end.substring(i, i + 1);

                String rest =
                        end.substring(0, i) +
                        end.substring(i + 1);

                completeAnagrams(start + letter, rest);
            }
        }
    }

    // Removes duplicate anagrams
    private void removeDupes()
    {
        ArrayList<String> noDupes = new ArrayList<String>();

        for (String s : anagrams)
        {
            if (!noDupes.contains(s))
            {
                noDupes.add(s);
            }
        }

        anagrams.clear();
        anagrams.addAll(noDupes);
    }

    // Selection sort
    private void sortAnagrams()
    {
        for (int i = 0; i < anagrams.size() - 1; i++)
        {
            int minIndex = i;

            for (int j = i + 1; j < anagrams.size(); j++)
            {
                if (anagrams.get(j).compareTo(anagrams.get(minIndex)) < 0)
                {
                    minIndex = j;
                }
            }

            String temp = anagrams.get(i);
            anagrams.set(i, anagrams.get(minIndex));
            anagrams.set(minIndex, temp);
        }
    }

    // Binary search
    public int searchAnagrams(String target)
    {
        int low = 0;
        int high = anagrams.size() - 1;

        while (low <= high)
        {
            int mid = (low + high) / 2;

            int compare = target.compareTo(anagrams.get(mid));

            if (compare == 0)
            {
                return mid;
            }
            else if (compare < 0)
            {
                high = mid - 1;
            }
            else
            {
                low = mid + 1;
            }
        }

        return -1;
    }

    // Used to get list of anagrams externally, do not remove
    public ArrayList<String> getAnagrams()
    {
        return anagrams;
    }
}