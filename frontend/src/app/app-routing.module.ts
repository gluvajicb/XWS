import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component/home-component.component';
import { AddArticleComponent } from './add-article/add-article.component';
import { MyArticlesComponent } from './my-articles/my-articles.component';
import { AssignReviewersComponent } from './assign-reviewers/assign-reviewers.component';
import { AddReviewComponent } from './add-review/add-review.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EditArticleComponent } from './edit-article/edit-article.component';
import { AddCoverletterComponent } from './add-coverletter/add-coverletter.component';
import { SearchComponent } from './search/search.component';
import { ViewArticleComponent } from './view-article/view-article.component';
import { ViewCoverletterComponent } from './view-coverletter/view-coverletter.component';
import { ViewReviewComponent } from './view-review/view-review.component';
import {AuthGuard} from './helpers/AuthGuard';
import { NewArticlesComponent } from './new-articles/new-articles.component';
import { ReviewedArticlesComponent } from './reviewed-articles/reviewed-articles.component';
import { ReviewsComponent } from './reviews/reviews.component';


const routes: Routes = [
  {path: '', component: HomeComponentComponent},
  {path: 'add-article', component: AddArticleComponent, canActivate: [AuthGuard]},
  {path: 'add-review/:id', component: AddReviewComponent, canActivate: [AuthGuard]},
  {path: 'my-articles', component: MyArticlesComponent, canActivate: [AuthGuard]},
  {path: 'assign-reviewers/:id', component: AssignReviewersComponent, canActivate: [AuthGuard]}, // Article ID
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'edit-article/:id', component: EditArticleComponent, canActivate: [AuthGuard]}, // Article ID
  {path: 'add-coverletter', component: AddCoverletterComponent, canActivate: [AuthGuard]},
  {path: 'search', component: SearchComponent, canActivate: [AuthGuard]},
  {path: 'view/article/:id', component: ViewArticleComponent, canActivate: [AuthGuard]}, // Article ID
  {path: 'view/coverletter/:id', component: ViewCoverletterComponent, canActivate: [AuthGuard]}, // CoverLetter ID
  {path: 'view/review/:id', component: ViewReviewComponent, canActivate: [AuthGuard]}, // Review ID
  {path: 'new-articles', component: NewArticlesComponent, canActivate: [AuthGuard]},
  {path: 'reviewed-articles', component: ReviewedArticlesComponent, canActivate: [AuthGuard]},
  {path: 'reviews/:id', component: ReviewsComponent, canActivate:[AuthGuard]} // Article ID

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
