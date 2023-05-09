let smallToBig = true;

class App extends React.Component {
    constructor(props) {
        super(props);

        this.incrementAmount = this.incrementAmount.bind(this);
        this.sortByYear = this.sortByYear.bind(this);

        this.state = {
            cars: [
                {
                    "manufacturer": "Toyota",
                    "model": "Rav4",
                    "year": 2008,
                    "stock": 3,
                    "price": 8500
                },
    
                {
                    "manufacturer": "Toyota",
                    "model": "Camry",
                    "year": 2009,
                    "stock": 2,
                    "price": 6500
                },
    
                {
                    "manufacturer": "Toyota",
                    "model": "Tacoma",
                    "year": 2016,
                    "stock": 1,
                    "price": 22000
                },
    
                {
                    "manufacturer": "BMW",
                    "model": "i3",
                    "year": 2012,
                    "stock": 5,
                    "price": 12000
                },
    
                {
                    "manufacturer": "Chevy",
                    "model": "Malibu",
                    "year": 2015,
                    "stock": 2,
                    "price": 10000
                },
    
                {
                    "manufacturer": "Honda",
                    "model": "Accord",
                    "year": 2013,
                    "stock": 1,
                    "price": 9000
                },
    
                {
                    "manufacturer": "Hyundai",
                    "model": "Elantra",
                    "year": 2013,
                    "stock": 2,
                    "price": 7000
                },
    
                {
                    "manufacturer": "Chevy",
                    "model": "Cruze",
                    "year": 2012,
                    "stock": 2,
                    "price": 5500
                },
    
                {
                    "manufacturer": "Dodge",
                    "model": "Charger",
                    "year": 2013,
                    "stock": 2,
                    "price": 16000
                },
    
                {
                    "manufacturer": "Ford",
                    "model": "Mustang",
                    "year": 2009,
                    "stock": 1,
                    "price": 8000
                },
    
            ]
        };
    }

    sortByYear() {
        if (smallToBig === true) {
            let i, j, n = this.state.cars.length;
            for (i = 0; i < n - 1; i++) {
                for (j = 0; j < n - i - 1; j++) {
                    if (this.state.cars[j].year < this.state.cars[j+1].year) {
                        let temp = this.state.cars[j];
                        this.state.cars[j] = this.state.cars[j+1];
                        this.state.cars[j+1] = temp;
                    }
                }
            }
            smallToBig = false;
        } else {
            let i, j, n = this.state.cars.length;
            for (i = 0; i < n - 1; i++) {
                for (j = 0; j < n - i - 1; j++) {
                    if (this.state.cars[j].year > this.state.cars[j+1].year) {
                        let temp = this.state.cars[j];
                        this.state.cars[j] = this.state.cars[j+1];
                        this.state.cars[j+1] = temp;
                    }
                }
            }
            smallToBig = true;
        }
        ReactDOM.render(<App />, document.getElementById("app"))
    }

    getTableData() {
        return this.state.cars.map(item => {
            return(
                <tr key={item.model}>
                    <td>{item.manufacturer}</td>
                    <td>{item.model}</td>
                    <td>{item.year}</td>
                    <td>{item.stock}</td>
                    <td>${item.price}.00</td>
                    <button key={item.model} data-letter={item.model} onClick={() => this.incrementAmount(item.model)}>Increment</button>
                </tr>
            )
        })
    }

    incrementAmount(model) {

        let cars = [...this.state.cars];
        let index = cars.findIndex(item => item.model === model);
        let newInt = this.state.cars[index].stock + 1;
        cars[index] = {stock: newInt, manufacturer: cars[index].manufacturer, model: cars[index].model, price: cars[index].price, year: cars[index].year};
        this.setState({cars});

        ReactDOM.render(<App />, document.getElementById("app"))
    }

    returnHeader() {
        return(
            <tr>
                <th>Manufacturer</th>
                <th>Model</th>
                <th onClick={this.sortByYear}>Year</th>
                <th>Stock</th>
                <th>Price</th>
                <th>Option</th>
            </tr>
        );
    }

    render() {
        return (
            <table>
                <tbody>
                    {this.returnHeader()}
                    {this.getTableData()}
                </tbody>
            </table>
        );
    };

}

ReactDOM.render(<App />, document.getElementById("app"))