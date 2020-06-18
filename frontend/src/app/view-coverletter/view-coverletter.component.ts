import { Component, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { CoverletterService } from '../services/coverletter.service';


@Component({
  selector: 'app-view-coverletter',
  templateUrl: './view-coverletter.component.html',
  styleUrls: ['./view-coverletter.component.css']
})
export class ViewCoverletterComponent implements OnInit {

  private headers = new HttpHeaders({'Content-Type': 'application/xml'});

  private id: any;
  private sub: any;

  constructor(private route: ActivatedRoute,
    private coverLetterService: CoverletterService,
    private router: Router) { }

    ngOnInit() {
      this.logCoverLetter();
    }
  
    logCoverLetter() {
      this.sub = this.route.params.subscribe(params => {
        this.id = params['id'];
      });
  
  
      this.coverLetterService.getCoverLetter(this.id).subscribe(
        result => {
  
          console.log(result);
        }
      );
    }

}
