import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { HttpClientModule, HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { ArticleService } from '../services/article.service';


declare var Xonomy: any;

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  constructor(private ArticleService: ArticleService,
              private router: Router) { }

  docSpec = {
    
    
    elements: {
      
      article: {

      },

      title: {
        hasText: true
      },

      publisher: {
        hasText: true
      },

      keywords: {
        menu: [{
          caption: 'Append a <keyword>',
          action: Xonomy.newElementChild,
          actionParameter: '<keyword></keyword>'
          }]
      },
      keyword: {
        hasText: true,
        menu: [{
          caption: 'Delete this <keyword>',
          action: Xonomy.deleteElement
          }, {
            caption: 'New <keyword> before this',
            action: Xonomy.newElementBefore,
            actionParameter: '<keyword/>'
            }, {
            caption: 'New <keyword> after this',
            action: Xonomy.newElementAfter,
            actionParameter: '<keyword/>'
            }
        ]
      },
      abstract: {
        menu: [{
          caption: 'Append a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
          }, {
          caption: 'Append a <keywords>',
          action: Xonomy.newElementChild,
          actionParameter: '<keywords></keywords>'
          }]
      },
      sections:{
        menu: [{
          caption: 'Append a <section>',
          action: Xonomy.newElementChild,
          actionParameter: '<section></section>'
        }]
      },
      section:{
        menu: [{
          caption: 'Append a <title>',
          action: Xonomy.newElementChild,
          actionParameter: '<title></title>'
        }, {
          caption: 'Append a <paragraph>',
          action: Xonomy.newElementChild,
          actionParameter: '<paragraph></paragraph>'
        }
        ]
      },
      authors: {
        menu: [{
          caption: 'Append an <author>',
          action: Xonomy.newElementChild,
          actionParameter: '<author></author>'
        }]
      },
      author:{
        menu: [{
          caption: 'Append a <name>',
          action: Xonomy.newElementChild,
          actionParameter: '<name></name>'
        },
        {
          caption: 'Append a <surname>',
          action: Xonomy.newElementChild,
          actionParameter: '<surname></surname>'
        },
        {
          caption: 'Append an <university>',
          action: Xonomy.newElementChild,
          actionParameter: '<university></university>'
        }
        ]
      },
      name:{
          hasText: true,
          menu: [
            {
              caption: 'Delete this <name>',
              action: Xonomy.deleteElement
            }
          ]
      },
      surname:{
        hasText: true,
        menu: [
          {
            caption: 'Delete this <surname>',
            action: Xonomy.deleteElement
          }
        ]
      },
      university:{
        menu: [
          {
            caption: 'Append a <name>',
            action: Xonomy.newElementChild,
            actionParameter: '<name></name>'
          },
          {
            caption: 'Append an <address>',
            action: Xonomy.newElementChild,
            actionParameter: '<address></address>'
          }
        ]
      },
      address:{
        hasText: true,
        menu: [
          {
            caption: 'Delete this <surname>',
            action: Xonomy.deleteElement
          }
        ]
      },
      paragraph: {
        menu: [
          {
            caption: 'Append a <text>',
            action: Xonomy.newElementChild,
            actionParameter: '<text></text>'
          },
          {
            caption: 'Delete this <paragraph>',
            action: Xonomy.deleteElement
          },
          {
            caption: 'New <paragraph> before this',
            action: Xonomy.newElementBefore,
            actionParameter: '<paragraph/>'
          }, {
            caption: 'New <paragraph> after this',
            action: Xonomy.newElementAfter,
            actionParameter: '<paragraph/>'
          }
        ]
      },
      text: {
        hasText: true
      },
      new_line: {
      }
      /*
      references: {
        menu: [
          {
            caption: 'Append a <reference>',
            action: Xonomy.newElementChild,
            actionParameter: '<reference></reference>'
          }
        ]
      },
      publication_date: {
        hasText: true
      },
      reference: {
        menu: [
          {
            caption: 'Append a <publication_date>',
            action: Xonomy.newElementChild,
            actionParameter: '<publication_date></publication_date>'
          },
          {
            caption: 'Append a <title>',
            action: Xonomy.newElementChild,
            actionParameter: '<title></title>'
          },
          {
            caption: 'Append a <publisher>',
            action: Xonomy.newElementChild,
            actionParameter: '<publisher></publisher>'
          }
        ]
      }
      */

    }
  };


  ngOnInit() {
    this.start();
  }

  start() {

    const xml = '<article xmlns="http://www.uns.ac.rs/XMLtim/Article" xmlns:msb="http://www.uns.ac.rs/XMLtim/Article">' + 
                  '<title>' +
                  '</title>' +
                  '<publisher>' + 
                  '</publisher>' +
                  '<authors>' +
                  '</authors>' +
                  '<abstract>' +
                  '</abstract>' +
                  '<sections>' +
                  '</sections>' +
                '</article>';
    
    const editor = document.getElementById('editor');
    Xonomy.render(xml, editor, this.docSpec);

  }

  submit() {
  
    const xml = Xonomy.harvest();

    console.log(xml);
    this.ArticleService.add(xml as string).subscribe(
      result => {
        this.router.navigate(['/my-articles']);
      }
    );
  }

}
