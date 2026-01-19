
import { UserDataState } from "./+user-data/user-data.reducer";
import { ModalsAppState } from "./+user-interface/user-interface.reducer";
import { UserState } from "./+users/users.reducer";

export interface SharedAppState {
    modals: ModalsAppState,
    user: UserState,
    userData: UserDataState
}
