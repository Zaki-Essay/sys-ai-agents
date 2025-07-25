import {Button, TextField} from "@vaadin/react-components";
import {useState} from "react";
import Markdown from "react-markdown";
import {FinancialAnalysisAgent} from "Frontend/generated/endpoints";
export default function Index(){
    const [company, setCompany] = useState<string>("");
    const [response, setResposne] = useState<string>("");

    async function askAgent(){
            FinancialAnalysisAgent.financialAnalysisReport(company)
                .then(resp=>{
                    setResposne(resp);
                });
    }
    return(
        <div className="p-3">
            <TextField style={{width:'80%'}} onChange={(e)=>setCompany(e.target.value)} value={company}></TextField>
            <Button onClick={askAgent} theme={"primary"}>Ask Agent</Button>
            <div>
                <Markdown>
                    {response}
                </Markdown>
            </div>
        </div>
    )
}