package rohan.dreamteam.services;

import rohan.dreamteam.fpldomain.initialData.InitialDataRoot;
import rohan.dreamteam.fpldomain.playerData.PlayerDataRoot;

public interface DataConverter {

	InitialDataRoot convertJsonToInitialDataRoot(final String json);

	PlayerDataRoot convertJsonToPlayerDataRoot(final String json);
}
