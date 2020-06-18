
export class SearchParams {
    abst: string;
    title: string;
    keyword: string;
    author: string;
    section: string;

    constructor(obj?: any) {
     this.abst = obj && obj.abst || null;
     this.title = obj && obj.title || null;
     this.keyword = obj && obj.keyword || null;
     this.author = obj && obj.author || null;
     this.section = obj && obj.section || null;
    }
}
