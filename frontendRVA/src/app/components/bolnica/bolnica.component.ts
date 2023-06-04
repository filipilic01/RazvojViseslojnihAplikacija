import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs/internal/Subscription';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';
import { BolnicaDialogComponent } from '../dialogs/bolnica-dialog/bolnica-dialog.component';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})
export class BolnicaComponent {

  //dependency injection
  constructor (private bolnicaService: BolnicaService, private dialog: MatDialog/*predstvlja modalni dijalog*/){}

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa','budzet', 'actions'];
  dataSource!: MatTableDataSource<Bolnica>;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;

  ngOnInit(){
    this.loadData();
  }

  loadData(){
    this.subscription=this.bolnicaService.getAllBolnica().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data); //tabeli prosledjujemo dobijene podatke 
        this.dataSource.sort=this.sort;
        this.dataSource.paginator=this.paginator;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      
      }
      );
  }

  public openDialog(flag: number, bolnica?: Bolnica): void {
    const dialogRef = this.dialog.open(BolnicaDialogComponent, {data: (bolnica?bolnica: new Bolnica())});//zelimo da sacuvamo dijalog nakon otvaranja
    dialogRef.componentInstance.flagBolDialog= flag;//vracamo instancu kreirane komponente dijaloga i smestamo vrednost flag-a unutar nje
    dialogRef.afterClosed().subscribe(res=> {if(res===1) this.loadData();})//nakon zatvaranja proveriti da li je uspesno izvrsena operacija
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }

  ngOnDestroy(): void {this.subscription.unsubscribe();}
  ngOnChanges(){this.loadData();}
}
