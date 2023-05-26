import java.util.Arrays;
import java.util.Comparator;

public class Sample {
    public static void main(String args[]) {

        Comparator<String> decreasing = (first, second) -> {
            if (first == null) {
                return -1;
            } else if (second == null) {
                return 1;
            } else {
                return first.compareTo(second);
            }
        };

        String words[] = new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat",
                "ratcatdogcat"};

        Arrays.sort(words, decreasing);
        for (int i = (words.length - 1); i >= 0; i--) {
            System.out.println(words[i]);
        }

    }
}

/*
 * ["retinula","alkalimetries","ofay","sifaka","antiweed",
 * "retinued","retinues","notation","semisweet","cocounseled","antiwear",
 * "reattempted","recouping","desegregations","axemen","storminesses",
 * "reconvene",
 * "reacquired","reacquires","sideline","laagered","defeatures","allotrope",
 * "ailanthuses","forehead","maestoso","ohed","reassigned","reaccredit",
 * "heishi",
 * "beflowered","resketched","resketches","aromas","fiercenesses","exoduses",
 * "indivisible","amreetas","axeman","nonego","ostium","mindfulnesses",
 * "unchurches",
 * "mesopause","unchurched","invigilator","mirages","inanes","basemen","inaner",
 * "wooded","wooden","amoeboid","unchurchly","showtimes","tricklier",
 * "reshoeing",
 * "remapping","psalmed","albedoes","invigilates","pemoline","invigilated",
 * "wilinesses","unneurotic","isopodan","uniformities","appalled","abomasal",
 * "elides",
 * "elided","antinomy","delightful","antinode","marquees","abomasus","abomasum",
 * "radiometries",
 * "mesosome","formalwear","microluxes","invocations","hereto","ostomies",
 * "onyxes","ballerinas",
 * "laundresses","remarrying","herein","pelota","replaying","delighters",
 * "hereon","hereof",
 * "hereat","shutoffs","hereby","chordates","frettier","deportable","ungraceful"
 * ,"demeaned","prelimited","inviolatenesses","surgeonfishes","repetitional",
 * "omen","resummoned","omer","omit","realised","realiser","realises",
 * "deindustrializes","deindustrialized","headraces","inadequate","denotation",
 * "sneerer","sneered","demeanor","ones","allopaths","influxes","deadbeat",
 * "unquietnesses","latino","onus","onto","latigo","mausolea","rechoosing",
 * "recursive","cassises","hermitages","depolished","upraiser","upraises",
 * "upraised","depolishes","myelin","latish","amoebean","halidome","anisoles",
 * "dreaminesses","replanning","pieforts","opal","opah","orpine","oped","opes",
 * "open","tolane","ostomy","orgone","bygone","rekindles","rekindled",
 * "outmaneuvered","agnomina","opus","exhalation","defloration","betime",
 * "unresistant","unabused","loosened","loosener","picarooned","betide","noesis"
 * ,"armoire","senores","copulation","alpine","uprooter","marinade","uprooted",
 * "paradisiacal","allopatries","orby","mopier","orad","oral","ordo","senoras",
 * "ores","arracks","mopish","reattempt","yeastier","woodbine","osar","sonata",
 * "retracing","oses","revivified","inalienable","revivifies"]
 */
