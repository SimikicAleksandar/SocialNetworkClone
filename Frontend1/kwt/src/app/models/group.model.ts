import { Post } from "./post.model";
import { User } from "./user.model";

export interface Group {
    id?: number;
    name: string;
    description: string;
    createdAt?: Date;
    createdBy?: User;
    isSuspended?: boolean;
    suspendedReason?: string;
    posts?: Post[];
}
