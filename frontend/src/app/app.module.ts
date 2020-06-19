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
import { PdfViewerModule} from 'ng2-pdf-viewer'
import {SecurityService} from './services/security/security.service';
import {authInterceptorProviders} from './helpers/auth.interceptor';
import { NewArticlesComponent } from './new-articles/new-articles.component';
import { ReviewedArticlesComponent } from './reviewed-articles/reviewed-articles.component';

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
    ViewReviewComponent,
    NewArticlesComponent,
    ReviewedArticlesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule, 
    PdfViewerModule
  ],
  providers: [
    SecurityService,
    authInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
