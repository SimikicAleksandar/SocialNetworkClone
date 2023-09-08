import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { CanActivateAuthGuardService } from './services/can-activate-auth.guard.service';
import { PostComponent } from './pages/post/post.component';
import { GroupComponent } from './pages/group/group.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent,},
  {path: 'auth/register', component: RegisterComponent, canActivate:[CanActivateAuthGuardService]},
  {path: 'auth/login', component: LoginComponent, canActivate:[CanActivateAuthGuardService]},
  {path: 'users/:username', component: ProfileComponent},
  {path: 'group', component: GroupComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: "**",  component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
