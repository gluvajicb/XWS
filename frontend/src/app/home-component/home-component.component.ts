import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';

@Component({
  selector: 'app-home-component',
  templateUrl: './home-component.component.html',
  styleUrls: ['./home-component.component.css']
})
export class HomeComponentComponent implements OnInit {

  constructor(private ArticleService: ArticleService) { }
  articles: [];
  ngOnInit(): void {
    this.getAllSubmitted();
  }

  getAllSubmitted() {
    this.ArticleService.getAllSubmitted().subscribe(
      result => {
        this.articles = result
        console.log(this.articles);
      }
    );
  }

}
