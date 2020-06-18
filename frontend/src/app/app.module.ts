import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { AddArticleComponent } from './add-article/add-article.component';
import { MyArticlesComponent } from './my-articles/my-articles.component';
import { AssignReviewersComponent } from './assign-reviewers/assign-reviewers.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { EditArticleComponent } from './edit-article/edit-article.component';
import { AddCoverletterComponent } from './add-coverletter/add-coverletter.component';
import { EditCoverletterComponent } from './edit-coverletter/edit-coverletter.component';
import { SearchComponent } from './search/search.component'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ViewArticleComponent } from './view-article/view-article.component';
import { ViewCoverletterComponent } from './view-coverletter/view-coverletter.component';
import { ViewReviewComponent } from './view-review/view-review.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponentComponent,
    AddArticleComponent,
    MyArticlesComponent,
    AssignReviewersComponent,
    AddReviewComponent,
    LoginComponent,
    RegisterComponent,
    EditArticleComponent,
    AddCoverletterComponent,
    EditCoverletterComponent,
    SearchComponent,
    ViewArticleComponent,
    ViewCoverletterComponent,
    ViewReviewComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
