import { User } from "./user.model";

export interface Post {
    id?: any;
    content: string;
    createdAt?: Date;
    createdBy?: User;
    containedBy?: number;
    isDeleted?: boolean;
    isEditing: boolean;
    updatedContent: string;
    isUpdating: boolean;
    comments: Comment[];
    showComments: boolean;
}

export interface Comment {
    id?: any;
    writtenBy?: User;
    text: string;
    createdAt?: string;
    isEditing?: boolean;
    updatedText: string;
    isUpdating: boolean;
}