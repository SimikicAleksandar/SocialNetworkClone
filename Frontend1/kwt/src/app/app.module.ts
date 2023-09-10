import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './pages/home/home.component';
import { RegisterComponent } from './pages/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './pages/login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { AuthenticationService } from './services/authentication.service';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './pages/profile/profile.component';
import { CanActivateAuthGuardService } from './services/can-activate-auth.guard.service';
import { PostComponent } from './pages/post/post.component';
import { JwtUtilsServiceService } from './services/jwt-utils-service.service';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { GroupComponent } from './pages/group/group.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    RegisterComponent,
    LoginComponent,
    NotFoundComponent,
    ProfileComponent,
    PostComponent,
    EditProfileComponent,
    ChangePasswordComponent,
    GroupComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [    
    AuthenticationService,
    UserService,
    CanActivateAuthGuardService,
    JwtUtilsServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
