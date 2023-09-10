import { Component, OnInit } from '@angular/core';
import { Group } from 'src/app/models/group.model';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { GroupService } from 'src/app/services/group.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {
  newGroupName!: string;
  newGroupDescription!: string;
  groups: Group[] = [];
  currentUser: any;
  loggedIn!:boolean;

  constructor(private http: HttpClient, private groupService: GroupService, private authService: AuthenticationService){}

  ngOnInit(): void {
    this.currentUser = this.authService.getCurrentUser();
    this.loadGroups();
    this.loggedIn = this.authService.isLoggedIn();
  }

  createGroup(): void{
    const group: Group = {
      name: this.newGroupName,
      description: this.newGroupDescription
    };

    this.groupService.save(group).subscribe(
      () => {
        console.log("Group successfully created !")
        this.loadGroups();
        this.newGroupName = '';
        this.newGroupDescription = '';
      },
      error => {
        console.log("Error: ", error);
      }
    )
  }

  loadGroups(){
    this.groupService.getAll().subscribe(
      groups => {
        this.groups = groups;
        
        this.groups.sort((a, b) => {
          if(a.createdAt && b.createdAt){
            return new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime();
          }else{
            return 0;
          }
        });
        this.groups.reverse();
      },
      error => {
        console.log(error);
      }
    )
  }

  deleteGroup(groupId: number) {
    if (confirm("Are you sure you want to delete this group?")) {
      this.groupService.delete(groupId).subscribe(
        () => {
          console.log("Group successfully deleted!");
          this.loadGroups(); // Reload groups after deleting one
        },
        error => {
          console.log("Error: ", error);
        }
      )
    }
  }


  showEditFormFlag: boolean = false;
  editGroupId: number | undefined;
  editGroupName: string = '';
  editGroupDescription: string = '';

  showEditForm(group: Group) {
    this.editGroupId = group.id;
    this.editGroupName = group.name;
    this.editGroupDescription = group.description;
    this.showEditFormFlag = true;
  }

  updateGroup() {
    this.groupService.update(this.editGroupId!, this.editGroupName, this.editGroupDescription).subscribe(
      () => {
        console.log("Group successfully updated!");
        this.cancelEdit(); // Hide the form after updating
        this.loadGroups(); // Refresh the groups list
      },
      error => {
        console.log("Error: ", error);
      }
    )
  }

  cancelEdit() {
    this.editGroupId = undefined;
    this.editGroupName = '';
    this.editGroupDescription = '';
    this.showEditFormFlag = false;
  }
}
