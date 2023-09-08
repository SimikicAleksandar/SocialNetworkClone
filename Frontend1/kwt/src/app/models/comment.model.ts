import { User } from "./user.model";

export interface Comment {
    id?: any;
    writtenBy?: User;
    text: string;
    createdAt?: string;
    isEditing?: boolean;
    updatedText: string;
    isUpdating: boolean;
}
