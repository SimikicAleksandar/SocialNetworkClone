import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ModelsComponent } from '../models/models.component';
import { PagesComponent } from './pages/pages.component';
import { ComponentsComponent } from './components/components.component';
import { ServicesComponent } from './services/services.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { CommentComponent } from './components/comment/comment.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GroupComponent } from './pages/group/group.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { PostComponent } from './pages/post/post.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    ModelsComponent,
    PagesComponent,
    ComponentsComponent,
    ServicesComponent,
    ChangePasswordComponent,
    CommentComponent,
    EditProfileComponent,
    NavbarComponent,
    GroupComponent,
    HomeComponent,
    LoginComponent,
    NotFoundComponent,
    PostComponent,
    ProfileComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
