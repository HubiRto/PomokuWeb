import React, {useState} from "react";
import {Button, Container, Grid, TextField} from "@mui/material";
import Navbar from "../components/Navbar";
import axios from "axios";

interface BFSInput {
    graph: number[][];
    start: number;
}


interface BFSResponse {
    traverse: number[];
    treeHeight: number;
    maxQueueSize: number;
    internalCount: number;
    externalCount: number;
    inOperationsCount: number;
    outOperationsCount: number;
}


const Bfs: React.FC = () => {

    const [inputFields, setInputFields] = useState<string[]>(['']);
    const [additionalInput, setAdditionalInput] = useState('');
    const [result, setResult] = useState<BFSResponse | null>(null);

    const handleAddInput = () => {
        setInputFields([...inputFields, '']);
    };

    const handleInputChange = (index: number, value: string) => {
        const newInputFields = [...inputFields];
        newInputFields[index] = value;
        setInputFields(newInputFields);
    };

    const handleAdditionalInputChange = (value: string) => {
        setAdditionalInput(value);
    };

    const handleRun = async () => {
        try {
            const graphInput: BFSInput = {
                graph: inputFields.map(row => row.split(',').map(Number)),
                start: parseInt(additionalInput)
            };
            const response = await axios.post<BFSResponse>('http://localhost:8080/api/v1/algorithm/bfs', graphInput);
            setResult(response.data);
        } catch (error) {
            console.error('Error:', error);
            // Handle error as needed
        }
    };

    const renderResult = () => {
        if (!result) return null;
        return (
            <div>
                <h2>Result:</h2>
                <table>
                    <tbody>
                    <tr>
                        <td>Traverse:</td>
                        {result.traverse.map((value, index) => (
                            <td key={index}>{value}</td>
                        ))}
                    </tr>
                    <tr>
                        <td>Tree Height:</td>
                        <td>{result.treeHeight}</td>
                    </tr>
                    <tr>
                        <td>Max Queue Size:</td>
                        <td>{result.maxQueueSize}</td>
                    </tr>
                    <tr>
                        <td>Internal Count:</td>
                        <td>{result.internalCount}</td>
                    </tr>
                    <tr>
                        <td>External Count:</td>
                        <td>{result.externalCount}</td>
                    </tr>
                    <tr>
                        <td>In Operations Count:</td>
                        <td>{result.inOperationsCount}</td>
                    </tr>
                    <tr>
                        <td>Out Operations Count:</td>
                        <td>{result.outOperationsCount}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    };

    return (
        <div>
            <Navbar/>
            <Container>
                <Grid container spacing={2}>
                    <Grid item xs={6}>
                        <p>Graph - Example Input: 1,2,3,4</p>
                        <div id="input-area">
                            {inputFields.map((inputField, index) => (
                                <div key={index} className="input-container">
                                    <TextField
                                        type="text"
                                        value={inputField}
                                        onChange={(e) => handleInputChange(index, e.target.value)}
                                        variant="outlined"
                                        label={`[${index + 1}]`}
                                    />
                                    <br/>
                                    <br/>
                                </div>
                            ))}
                        </div>
                        <Button id="add-input" onClick={handleAddInput} variant="contained" color="primary">
                            +
                        </Button>
                    </Grid>
                    <Grid item xs={6}>
                        <p>Start index</p>
                        <TextField
                            type="text"
                            value={additionalInput}
                            onChange={(e) => handleAdditionalInputChange(e.target.value)}
                            variant="outlined"
                            label="Number of Index"
                        />
                        <br/>
                        <br/>
                        <Button id="run-button" onClick={handleRun} variant="contained" color="primary">
                            Run
                        </Button>
                    </Grid>
                </Grid>
                {renderResult()}
            </Container>
        </div>
    );
};

export default Bfs;