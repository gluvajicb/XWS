import { Component, OnInit } from '@angular/core';
import { ArticleViewComponent } from '../article-view/article-view.component';

@Component({
  selector: 'app-my-articles',
  templateUrl: './my-articles.component.html',
  styleUrls: ['./my-articles.component.css'],
})


export class MyArticlesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
