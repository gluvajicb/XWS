import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from '../services/token-storage/token-storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  isAdmin = false;
  username: string;
  title = 'Pomoz\'Boze';
  isAuthor = false;
  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes('editor');
      this.isAuthor = this.roles.includes('author');

      this.username = user.username;
    }
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
