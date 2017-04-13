package mbk.raspberry_pi;

/**
 * Created by Khalid Bhai on 4/10/2017.
 */

public class All_Function {

    public String action[] = {"turn on", "turn off", "unlock", "lock", "status", "temperature"};
    public String noun[] = {"main light", "main door", "stove"};

    public String get_action(String speechOutput) {
        int i = 0;
        while (action[i] != null & i < action.length - 1) {
            if (speechOutput.contains(action[i])) {
                return action[i];
            } else {
                i++;

            }
        }
        return null;
    }

    public String get_noun(String speechOutput) {
        int i = 0;
        while (noun[i] != null & i < noun.length - 1) {
            if (speechOutput.contains(noun[i])) {
                return noun[i];
            } else
                i++;
        }
        return null;
    }


    public boolean ask_to_confirm(String action_todo, String noun_todo) {
        boolean act = false;
        if (action_todo == null || noun_todo == null) {
        } else {
            act = true;
        }
        return act;
    }


}
