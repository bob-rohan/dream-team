package rohan.dreamteam.transformers;

import rohan.dreamteam.fpldomain.initialdata.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerdata.PlayerDataRoot;

public interface DataConverter {

	InitialDataRoot convertJsonToInitialDataRoot(final String json);

	PlayerDataRoot convertJsonToPlayerDataRoot(final String json);
}
